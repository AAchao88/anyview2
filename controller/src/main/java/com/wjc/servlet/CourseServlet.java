package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.CourseService;
import com.wjc.UserService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.UserServiceImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.ResultInfo;
import com.wjc.pojo.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/course/*")@Slf4j
public class CourseServlet extends BaseServlet{

    /**
     * 查找课程
     * @param request
     * @param response
     */
    public void findCourse(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        //通过这个user去查所有信息，再利用className查找course
        UserService userService = new UserServiceImp();
        User returnUser = userService.getPersonalInformation(user);
        CourseService courseService = new CourseServiceImp();
        List<Course> courseList = courseService.findCourse(returnUser);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        //使用session保存课程信息，以便给后面提供练习信息的查询依据
//        session.setAttribute("course",courseList);

        if (courseList.size() == 0){
            resultInfo.setSuccess(false);
            resultInfo.setMessage("您当前没有课程，有问题请联系老师。");
        }else {
            resultInfo.setSuccess(true);
            resultInfo.setData(courseList);
        }
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }

    }
}
