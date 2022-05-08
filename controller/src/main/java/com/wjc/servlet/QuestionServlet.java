package com.wjc.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/question/*")@Slf4j
public class QuestionServlet extends BaseServlet{

    public void findQuestion(HttpServletRequest request, HttpServletResponse response){
        //通过session获取用户选中的练习信息

    }
}
