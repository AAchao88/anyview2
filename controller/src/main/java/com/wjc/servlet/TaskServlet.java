package com.wjc.servlet;

import com.wjc.CourseService;
import com.wjc.TaskService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        //通过课程名查询练习
        CourseService courseService = new CourseServiceImp();
        Course course = courseService.findCourseInfo(courseName);
        TaskService taskService = new TaskServiceImp();
        List<Task> taskList = taskService.findTask(course);

        //使用session保存用户在courseName课程的所有练习信息
        HttpSession session = request.getSession();
        session.setAttribute(courseName,taskList);





    }
}
