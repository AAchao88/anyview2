package com.wjc.servlet;

import com.wjc.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/UserServlet")@Slf4j
public class UserServlet extends BaseServlet{
    /**
     * 登录的方法
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response){
        //从HTML获取所有参数并封装成对象
        Map<String,String[]> map = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            log.error("BeanUtils出错，无法将map封装成user对象");
        }



        Map<String,String[]> map = req.getParameterMap();
        User loginUser = new User();
        try{
            BeanUtils.populate(loginUser,map);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }


        //调用操作数据库的方法，查询用户，传进封装好的对象，返回一个对象
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        //判断该user是否正确
        if(user.getUsername() == null){
            //错误，则跳转到错误页面
            req.getRequestDispatcher("/failServlet").forward(req,resp);
        }else {
            //成功则将返回的对象共享，以便成功页面使用
            req.setAttribute("user",user);
            req.getRequestDispatcher("/successServlet").forward(req,resp);
        }
    }
}
