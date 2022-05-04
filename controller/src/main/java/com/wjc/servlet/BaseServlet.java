package com.wjc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //获取请求路径
//        String uri = req.getRequestURI();     //    /user/login
//        //获取方法名称
//        String methodName = uri.substring(uri.lastIndexOf('/')+1);
//        Method method = null;
//        try {
//            method = this.getClass().getMethod(methodName, HttpServletResponse.class,HttpServletResponse.class);
//            method.invoke(this,req,resp);
//        } catch (NoSuchMethodException | IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取访问方法
            String methodname = request.getParameter("method");
            //获取指定类的对象
            Class<? extends BaseServlet> clazz =this.getClass();

            //获取方法
            Method method = clazz.getMethod(methodname, HttpServletRequest.class, HttpServletResponse.class);
            //使用方法
            method.invoke(this,request,response);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}

