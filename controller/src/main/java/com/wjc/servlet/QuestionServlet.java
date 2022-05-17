package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.CourseService;
import com.wjc.QuestionService;
import com.wjc.ReplyService;
import com.wjc.TaskService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.QuestionServiceImp;
import com.wjc.imp.ReplyServiceImp;
import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.*;
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

    /**
     * 学生查找题目
     * @param request
     * @param response
     */
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

    /**
     * 选择题自动赋分，简答题把作答存进reply表
     * @param request
     * @param response
     */
    public void batch(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //从HTML获取所有参数并封装成对象
        Map<String, String[]> map = request.getParameterMap();

        //通过课程名和练习名查找所有题目
        QuestionService questionService = new QuestionServiceImp();
        CourseService courseService = new CourseServiceImp();
        TaskService taskService = new TaskServiceImp();
        ReplyService replyService = new ReplyServiceImp();
        List<Question> questionList = questionService.findQuestion((String) session.getAttribute("taskName"), (String) session.getAttribute("courseName"));

        /*通过taskName和courseName和user_id查找task（已有部分该方法），先获得原来的 score（查），再通过
            task_id 加上
         *  questionList.get(i).getScore （改）, */
        Course course = courseService.findCourseInfo((String) session.getAttribute("courseName"));
        Task task = taskService.getTask(course,user, (String) session.getAttribute("taskName"));
        //学生提交作答后增加的分数,已完成的题目数，状态
        long addScore = 0;
        if(map.size() == questionList.size()){
            task.setStatus(1);
        }
        task.setCompleted(map.size());
        for(String key : map.keySet()){
            int k = Integer.parseInt(key);

                if(questionList.get(k-1).getType() == 1){
                    if(map.get(key)[0].equals(questionList.get(k-1).getAnswer())){
                        addScore = addScore+questionList.get(k-1).getScore();
                    }
                }else {
                    if (questionList.get(k-1).getType() == 2){
                        StringBuilder reply = new StringBuilder();
                        for(int j = 0;j<map.get(key).length;j++){
                            reply.append(map.get(key)[j]);
                        }
                        if(reply.toString().equals(questionList.get(k-1).getAnswer())){
                            addScore = addScore+questionList.get(k-1).getScore();
                        }
                        //提交给老师
                    }else {
                        //把简答题答案存进reply表
                        Reply reply = new Reply();
                        reply.setUser_id(user.getId());
                        reply.setQuestion_id(questionList.get(k-1).getId());
                        reply.setReply(map.get(key)[0]);
                        replyService.insertReply(reply);

                    }
            }
        }
        //获取原来作业的分数 和 题目的分值
        long taskScore = task.getScore();
        taskService.changeScore(task,taskScore+addScore,task.getCompleted(),task.getStatus());
//        System.out.println("成功了");
    }


    /**
     * 批改作业的某个题目
     * @param request
     * @param response
     */
    public void batchQuestion(HttpServletRequest request, HttpServletResponse response){
        String questionId = request.getParameter("questionId");
        HttpSession session = request.getSession();
        if (null != questionId){
            session.setAttribute("questionId",questionId);
        }else {
            QuestionService questionService = new QuestionServiceImp();
            Question question = questionService.findQuestionById((Long) session.getAttribute("questionId"));
            //通过question_id查询reply
            ReplyService replyService = new ReplyServiceImp();
            Reply reply = replyService.findReply((Long) session.getAttribute("questionId"));

            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setSuccess(true);
            resultInfo.setData(question);
            resultInfo.setData2(reply);
            try {
                mapper.writeValue(response.getOutputStream(),resultInfo);
            } catch (IOException e) {
                log.error("响应输出流出错");
            }
        }
    }

    /**
     * 管理员 获得所有题目
     * @param request
     * @param response
     */
    public void getAllQuestion(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        QuestionService questionService = new QuestionServiceImp();
        List<Question> questionList = questionService.getAllQuestion();

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        if (questionList.size() == 0){
            resultInfo.setSuccess(false);
            resultInfo.setMessage("题库暂无题目请先添加");
        }else {
            resultInfo.setSuccess(true);
            resultInfo.setData(questionList);
        }

        try {
            mapper.writeValue(response.getOutputStream(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }

    /**
     * 管理员删除题目
     * @param request
     * @param response
     */
    public void deleteQuestionByManager(HttpServletRequest request, HttpServletResponse response){
        long question_id = Long.parseLong(request.getParameter("question_id"));
        QuestionService questionService = new QuestionServiceImp();
        questionService.deleteQuestionByManager(question_id);

        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        if (questionService.deleteQuestionByManager(question_id)){
            resultInfo.setSuccess(true);
        }else {
            resultInfo.setSuccess(false);
            resultInfo.setMessage("删除失败");
        }
        try {
            mapper.writeValue(response.getOutputStream(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }

}
