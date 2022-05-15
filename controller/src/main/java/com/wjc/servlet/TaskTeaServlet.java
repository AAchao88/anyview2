package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.QuestionDao;
import com.wjc.QuestionService;
import com.wjc.TaskTeaService;
import com.wjc.imp.QuestionDaoImp;
import com.wjc.imp.QuestionServiceImp;
import com.wjc.imp.TaskTeaServiceImp;
import com.wjc.pojo.Question;
import com.wjc.pojo.ResultInfo;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/tasktea/*")@Slf4j
public class TaskTeaServlet extends BaseServlet {

    /**
     * 教师增加作业
     * @param request
     * @param response
     */
    public void addTaskTea(HttpServletRequest request, HttpServletResponse response) {
        //获取教师信息
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //从HTML获取所有参数并封装成对象
        Map<String, String[]> map = request.getParameterMap();
        //将请求的作业信息封装进Tasktea
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(user.getId());

        List<String> classNames = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        int i = 0;
        for (String key : map.keySet()) {
            if (i < 4) {
                switch (key) {
                    case "taskName":
                        tasktea.setTaskName(map.get("taskName")[0]);
                        break;
                    case "courseName":
                        tasktea.setCourseName(map.get("courseName")[0]);
                        break;
                    case "className": {
                        for (int j = 0; j < map.get("className").length; j++) {
                            classNames.add(j, map.get("className")[j]);
                        }
                        break;
                    }
                    case "total":
                        tasktea.setTotal(Long.parseLong(map.get("total")[0]));
                        break;
                }
            } else {
                break;
            }
            i++;
        }
        //删除作业信息，便于下面存储题目信息
        map.remove("taskName");
        map.remove("courseName");
        map.remove("className");
        map.remove("total");
        int a = 0;
        long totalScore = 0;
        for(String key:map.keySet()){
            Question question = new Question();
            question.setQuestionName(map.get(key)[0]);
            question.setType(Integer.parseInt(map.get(key)[1]));
            question.setScore(Long.parseLong(map.get(key)[2]));
            totalScore = Long.parseLong(map.get(key)[2]) + totalScore;
            question.setQuestionContent(map.get(key)[3]);
            question.setAnswer(map.get(key)[4]);
            questions.add(a,question);
            a++;
        }

        tasktea.setScore(totalScore);
        //插入tasktea
        TaskTeaService taskTeaService = new TaskTeaServiceImp();
        for(int b = 0;b<classNames.size();b++){
            tasktea.setClassName(classNames.get(b));
            taskTeaService.addTaskTea(tasktea);
        }
        //插入question
        QuestionService questionService = new QuestionServiceImp();
        for(int c = 0;c<questions.size();c++){
            questionService.addQuestion(tasktea,questions.get(c));
        }

        response.setContentType("application/json;charset=utf-8");
        //创建转Jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        //创建回显信息对象
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMessage(String.valueOf(totalScore));
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }

    /**
     * 修改作业
     * @param request
     * @param response
     */
    public void changeTaskTea(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Tasktea tasktea = (Tasktea) session.getAttribute("tasktea");
        List<Question> questions = (List<Question>) session.getAttribute("questions");
        User user = (User) session.getAttribute("user");
        //通过teacher_id和oldtaskName 去查找原来的tasktea的id，方便做后面修改的定位
        TaskTeaService taskTeaService = new TaskTeaServiceImp();
        tasktea.setTeacher_id(user.getId());
        long id = tasktea.getId();

        Map<String, String[]> map = request.getParameterMap();
        int i = 0;
        for (String key : map.keySet()) {
            if (i < 4) {
                switch (key) {
                    case "taskName":
                        tasktea.setTaskName(map.get("taskName")[0]);
                        break;
                    case "courseName":
                        tasktea.setCourseName(map.get("courseName")[0]);
                        break;
                    case "className": {
                        tasktea.setClassName(map.get(key)[0]);
                        break;
                    }
                }
            } else {
                break;
            }
            i++;
        }
        //删除作业信息，便于下面存储题目信息
        map.remove("taskName");
        map.remove("courseName");
        map.remove("className");
//            map.remove("total");
        int a = 0;
        long oldScore = tasktea.getScore();
        for(String key:map.keySet()){
            oldScore = oldScore-questions.get(a).getScore()+Long.parseLong(map.get(key)[0]);
            questions.get(a).setScore(Long.parseLong(map.get(key)[0]));
            a++;
        }
        tasktea.setScore(oldScore);
        //修改作业
        taskTeaService.changeTaskTea(tasktea,id);
        //修改题目
        QuestionService questionService = new QuestionServiceImp();
        for(int c = 0;c<questions.size();c++){
            questionService.changeQuestion(tasktea,questions.get(c));
        }

    }

    /**
     * 修改作业时获取原来的tasktea信息
     * @param request
     * @param response
     */
    public void getTaskTea(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //从教师首页获取所有原来taskName参数
        String oldTaskName = request.getParameter("oldTaskName");
        if (oldTaskName != null) {
            session.setAttribute("oldTaskName", oldTaskName);
        } else {
            //获取教师信息
            User user = (User) session.getAttribute("user");
            //通过teacher_id和oldtaskName 去查找原来的tasktea的id，方便做后面修改的定位
            TaskTeaService taskTeaService = new TaskTeaServiceImp();
            Tasktea tasktea = taskTeaService.findTaskTea((String) session.getAttribute("oldTaskName"), user);
//            tasktea.setTeacher_id(user.getId());
//            long id = tasktea.getId();
            QuestionDao questionDao = new QuestionDaoImp();
            List<Question> questions = questionDao.findQuestion(tasktea.getTaskName(),tasktea.getCourseName());
            //把taskTea存进session
            session.setAttribute("tasktea",tasktea);
            session.setAttribute("questions",questions);

            response.setContentType("application/json;charset=utf-8");
            //创建转Jackson核心对象
            ObjectMapper mapper = new ObjectMapper();
            //创建回显信息对象
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setData(tasktea);
            resultInfo.setData2(questions);
            try {
                mapper.writeValue(response.getWriter(),resultInfo);
            } catch (IOException e) {
                log.error("响应输出流出错");
            }
        }
    }

    /**
     * 复用作业
     * @param request
     * @param response
     */
    public void multiplexTaskTea(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        //从教师首页获取所有原来taskName参数
        String oldTaskName = request.getParameter("oldTaskName");
        if (oldTaskName != null) {
            session.setAttribute("oldTaskName", oldTaskName);
        } else {
            //获取教师信息
            User user = (User) session.getAttribute("user");
            //通过teacher_id和oldtaskName 去查找原来的tasktea
            TaskTeaService taskTeaService = new TaskTeaServiceImp();
            Tasktea tasktea = taskTeaService.findTaskTea((String) session.getAttribute("oldTaskName"), user);
            tasktea.setTeacher_id(user.getId());

            Map<String, String[]> map = request.getParameterMap();
            List<String> classNames = new ArrayList<>();
            for (String key : map.keySet()) {
                switch (key) {
                    case "taskName":
                        tasktea.setTaskName(map.get("taskName")[0]);
                        break;
                    case "courseName":
                        tasktea.setCourseName(map.get("courseName")[0]);
                        break;
                    case "className": {
                        for (int j = 0; j < map.get("className").length; j++) {
                            classNames.add(j, map.get("className")[j]);
                        }
                        break;
                    }
                }
            }
            for (int b = 0; b < classNames.size(); b++) {
                tasktea.setClassName(classNames.get(b));
                taskTeaService.addTaskTea(tasktea);
            }
        }
    }
}
