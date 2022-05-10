package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.UserService;
import com.wjc.imp.UserServiceImp;
import com.wjc.pojo.ResultInfo;
import com.wjc.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/user/*")@Slf4j
public class UserServlet extends BaseServlet{
    /**
     * 登录的方法
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) {

        //从HTML获取所有参数并封装成对象
        Map<String, String[]> map = request.getParameterMap();
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            log.error("BeanUtils出错，无法将map封装成user对象");
        }
        UserService userService = new UserServiceImp();
        User returnUser = userService.login(loginUser);

        response.setContentType("application/json;charset=utf-8");
        //创建转Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        //创建回显信息对象
        ResultInfo resultInfo = new ResultInfo();

        if(returnUser == null){
            //用户不存在，设置回显信息
            resultInfo.setSuccess(false);
            resultInfo.setMessage("学工号或密码输入错误");
        }else {
            //用户存在，设置回显信息
            resultInfo.setSuccess(true);
            resultInfo.setMessage("登录成功");
            HttpSession session = request.getSession();
            session.setAttribute("user",returnUser);
        }
        //返回数据
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错！");
        }
    }

    /**
     * 个人中心
     * @param request
     * @param response
     */
    public void personalCenter(HttpServletRequest request,HttpServletResponse response){
        //通过session获取用户基本信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //获取用户全部信息
        UserService userService = new UserServiceImp();
        User returnUser = userService.getPersonalInformation(user);
        response.setContentType("application/json;charset=utf-8");
        //创建转Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        //创建回显信息对象
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setData(returnUser);
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错！");
        }
    }

    /**
     * 登出方法
     * @param request
     * @param response
     */
    public void signOut(HttpServletRequest request,HttpServletResponse response){
        //通过session获取用户基本信息,然后删除user信息
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        log.info("用户退出登录");
    }

}
