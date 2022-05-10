package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.CourseService;
import com.wjc.QuestionService;
import com.wjc.TaskService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.QuestionServiceImp;
import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.Question;
import com.wjc.pojo.ResultInfo;
import com.wjc.pojo.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/question/*")@Slf4j
public class QuestionServlet extends BaseServlet{

    public void findQuestion(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String courseName = request.getParameter("courseName");
        String taskName = request.getParameter("taskName");
        if(courseName != null && taskName != null){
            System.out.println(courseName+taskName);
            session.setAttribute("courseName",courseName);
            session.setAttribute("taskName",taskName);
        }
        User user = (User) session.getAttribute("user");

        System.out.println(session.getAttribute("taskName"));
        System.out.println(session.getAttribute("courseName"));
        System.out.println(user);

        //通过课程名和练习名查找所有题目
        QuestionService questionService = new QuestionServiceImp();
        List<Question> questionList = questionService.findQuestion((String) session.getAttribute("taskName"), (String) session.getAttribute("courseName"));

        response.setContentType("application/json;charset=utf-8");

        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();

        if (questionList.size() == 0){
            resultInfo.setSuccess(false);
            resultInfo.setMessage("该作业暂无题目，有问题请联系老师。");
        }else {
            resultInfo.setSuccess(true);
            resultInfo.setMessage((String) session.getAttribute("courseName")+":"+(String) session.getAttribute("taskName"));
            resultInfo.setData(questionList);
        }
        try {
            mapper.writeValue(response.getOutputStream(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }

    public void batch(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //从HTML获取所有参数并封装成对象
        Map<String, String[]> map = request.getParameterMap();

        //通过课程名和练习名查找所有题目
        QuestionService questionService = new QuestionServiceImp();
        CourseService courseService = new CourseServiceImp();
        TaskService taskService = new TaskServiceImp();
        List<Question> questionList = questionService.findQuestion((String) session.getAttribute("taskName"), (String) session.getAttribute("courseName"));

        Course course = courseService.findCourseInfo((String) session.getAttribute("courseName"));


        int i = 0;
        for(String key : map.keySet()){
            //  1 为单选题
            if(questionList.get(i).getType() == 1){
                if(map.get(key)[0].equals(questionList.get(i).getAnswer())){
                    /*通过taskName和courseName和user_id查找task（已有部分该方法），先获得原来的 score（查），再加上
                    *  questionList.get(i).getScore （改）, */

                }
            }else {
                if (questionList.get(i).getType() == 2){
                    StringBuilder reply = new StringBuilder();
                    for(int j = 0;j<map.get(key).length;j++){
                        reply.append(map.get(key)[j]);
                    }
                    if(reply.equals(questionList.get(i).getAnswer())){

                    }
                    //提交给老师
                }else {

                }
            }
            i++;
        }

    }
}
