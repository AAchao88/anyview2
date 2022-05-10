package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.CourseService;
import com.wjc.TaskService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.ResultInfo;
import com.wjc.pojo.Task;
import com.wjc.pojo.User;
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
     * 通过课程名查询所有练习并返回
     * @param request
     * @param response
     */
    public void findTask(HttpServletRequest request, HttpServletResponse response){
        //获取用户所选的课程名
        String courseName = request.getParameter("courseName");
        //通过session获取user_id
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println(courseName);

        //通过课程名查询练习
        CourseService courseService = new CourseServiceImp();
        Course course = courseService.findCourseInfo(courseName);
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
            resultInfo.setData(taskList);
        }
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }

//        //转发给QuestionServlet,并课程名传过去
//        request.setAttribute("courseName",courseName);
//        try {
//            request.getRequestDispatcher("/question/findQuestion").forward(request,response);
//        } catch (ServletException e) {
//            log.warn("Servlet异常");
//        } catch (IOException e) {
//            log.error("IO异常");
//        }
    }
}
