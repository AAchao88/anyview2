package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.CourseService;
import com.wjc.QuestionService;
import com.wjc.TaskService;
import com.wjc.UserService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.QuestionServiceImp;
import com.wjc.imp.TaskServiceImp;
import com.wjc.imp.UserServiceImp;
import com.wjc.pojo.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/task/*")@Slf4j
public class TaskServlet extends BaseServlet{
    /**
     * 学生通过课程名查询所有练习并返回
     * @param request
     * @param response
     */
    public void findTask(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String courseName1 = request.getParameter("courseName");
        if(courseName1 != null){
            session.setAttribute("courseName",courseName1);
        }
        User user = (User) session.getAttribute("user");
        //通过课程名和user_id查询练习
        CourseService courseService = new CourseServiceImp();
        Course course = courseService.findCourseInfo((String) session.getAttribute("courseName"));
        TaskService taskService = new TaskServiceImp();
        List<Task> taskList = taskService.findTask(course,user);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        if (taskList.size() == 0){
            resultInfo.setSuccess(false);
            resultInfo.setMessage("该课程暂无作业，有问题请联系老师。");
        }else {
            resultInfo.setSuccess(true);
            resultInfo.setMessage((String) session.getAttribute("courseName"));
            resultInfo.setData(taskList);
        }
        try {
            mapper.writeValue(response.getOutputStream(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }

    /**
     * 教师查找所有作业
     * @param request
     * @param response
     */
    public void findTeaTask(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        TaskService taskService = new TaskServiceImp();
        List<Tasktea> taskteas = taskService.findTeaTask(user);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        if(taskteas.size() == 0){
            resultInfo.setSuccess(false);
            resultInfo.setMessage("您暂未添加任何作业");
        }else {
            resultInfo.setSuccess(true);
            resultInfo.setData(taskteas);
        }
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }

    /**
     * 教师通过teacher_id和taskName查询某作业的所有学生记录
     * @param request
     * @param response
     */
    public void getBatchTask(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        String taskName = (String) session.getAttribute("taskName");
            //  通过session.setAttribute("taskName",taskName);查询task
            User user = (User) session.getAttribute("user");
            TaskService taskService = new TaskServiceImp();
            Tasktea tasktea = new Tasktea();
            tasktea.setTeacher_id(user.getId());
            tasktea.setTaskName((String) session.getAttribute("taskName"));
            //获取所有作业
            List<Task> taskList = taskService.getBatchTask(tasktea);
            //获取每个作业对应学生的信息
            UserService userService = new UserServiceImp();
            List<User> userList = new ArrayList<>();
            for(int s= 0;s<taskList.size();s++){
                userList.add(s, userService.findUserById(taskList.get(s).getUser_id()));
            }
            //获取该作业的所有题目
            QuestionService questionService = new QuestionServiceImp();
            CourseService courseService = new CourseServiceImp();
            List<Question> questions = new ArrayList<>();
            for(int c = 0,e = 0;c < taskList.size();c++,e++){
                Course course = courseService.findCourseById(taskList.get(c).getCourse_id());
                List<Question> questions1 = questionService.findQuestion(taskList.get(c).getTaskName(),course.getCourseName());
                for(int d = 0;d<taskList.get(0).getTotal();d++,e++){
                    questions.add(e,questions1.get(d));
                }
            }

            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            ResultInfo resultInfo = new ResultInfo();
            if(taskList.size() == 0){
                resultInfo.setSuccess(false);
                resultInfo.setMessage("该作业暂未发布，尚未有学生记录");
            }else {
                resultInfo.setSuccess(true);
                resultInfo.setData(taskList);
                resultInfo.setData2(questions);
                resultInfo.setData3(userList);
            }
            try {
                mapper.writeValue(response.getWriter(),resultInfo);
            } catch (IOException e) {
                log.error("响应输出流出错");
            }

    }

    /**
     * 批改作业时保存taskName信息
     * @param request
     * @param response
     */
    public void saveBatchTask(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String taskName = request.getParameter("taskName");
        session.setAttribute("taskName",taskName);
    }

    /**
     * 批改作业时给某解答题赋分
     * @param request
     * @param response
     */
    public void batchTask(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String taskName = (String) session.getAttribute("taskName");

        long score = Long.parseLong(request.getParameter("score"));


    }
}
