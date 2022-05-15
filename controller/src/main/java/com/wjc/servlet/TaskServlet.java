package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.CourseService;
import com.wjc.TaskService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.*;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
//            System.out.println("jjjjjjjjj");
            session.setAttribute("courseName",courseName1);
        }
        //获取用户所选的课程名
//        String courseName = request.getParameter("courseName");
        //通过session获取user_id，并把课程名

        User user = (User) session.getAttribute("user");
//        session.setAttribute(courseName,courseName);

//        System.out.println(session.getAttribute("courseName"));
//        System.out.println(user);

        //通过课程名和user_id查询练习
        CourseService courseService = new CourseServiceImp();
        Course course = courseService.findCourseInfo((String) session.getAttribute("courseName"));

//        System.out.println(course);

        TaskService taskService = new TaskServiceImp();
        List<Task> taskList = taskService.findTask(course,user);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();

        //判断当前与作业截止时间的前后，更改operate的值
//        for(int i = 0; i<taskList.size();i++){
//
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            String s = simpleDateFormat.format(new Date(String.valueOf((taskList.get(i).getDeadline()))));
//            System.out.println(s);
//
//        }

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
        String taskName = request.getParameter("taskName");
        if(taskName != null){
            session.setAttribute("taskName",taskName);
        }else {
            //  通过session.setAttribute("taskName",taskName);查询task
            User user = (User) session.getAttribute("user");
            TaskService taskService = new TaskServiceImp();
            Tasktea tasktea = new Tasktea();
            tasktea.setTeacher_id(user.getId());
            tasktea.setTaskName((String) session.getAttribute("taskName"));
            //获取所有作业
            List<Task> taskList = taskService.getBatchTask(tasktea);
            //获取该作业的所有题目

            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            ResultInfo resultInfo = new ResultInfo();
            if(taskList.size() == 0){
                resultInfo.setSuccess(false);
                resultInfo.setMessage("该作业暂未发布，尚未有学生记录");
            }else {
                resultInfo.setSuccess(true);
                resultInfo.setData(taskList);
            }
            try {
                mapper.writeValue(response.getWriter(),resultInfo);
            } catch (IOException e) {
                log.error("响应输出流出错");
            }

        }

    }

}
