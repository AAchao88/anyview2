package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.QuestionService;
import com.wjc.imp.QuestionServiceImp;
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

@WebServlet("/question/*")@Slf4j
public class QuestionServlet extends BaseServlet{

    public void findQuestion(HttpServletRequest request, HttpServletResponse response){
        //获取用户所选的作业名,课程名
        String taskName = request.getParameter("taskName");
        String courseName = request.getParameter("courseName");
        //通过session获取user_id
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println(taskName);

        //通过作业名和课程名查询题目
        QuestionService questionService = new QuestionServiceImp();
        List<Question> questionList = questionService.findQuestion(taskName,courseName);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();

        //判断当前与作业截止时间的前后，更改operate的值

        if (questionList.size() == 0){
            resultInfo.setSuccess(false);
            resultInfo.setMessage("该作业暂无题目，有问题请联系老师。");
        }else {
            resultInfo.setSuccess(true);
            resultInfo.setMessage(courseName);
            resultInfo.setData(questionList);
        }
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }

    public void batch(HttpServletRequest request, HttpServletResponse response){

    }
}
