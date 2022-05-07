package com.wjc.servlet;

import com.wjc.UserService;
import com.wjc.imp.UserServiceImp;
import com.wjc.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CourseServlet extends BaseServlet{

    public void findCourse(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //通过这个user去查所有信息，再利用className查找course
        UserService userService = new UserServiceImp();
        User returnUser = userService.getPersonalInformation(user);

    }
}
