package com.wjc.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjc.CourseService;
import com.wjc.TaskService;
import com.wjc.UserService;
import com.wjc.imp.CourseServiceImp;
import com.wjc.imp.TaskServiceImp;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/course/*")@Slf4j
public class CourseServlet extends BaseServlet{

    /**
     * 学生查找课程
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


    public void findCourseTea(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        CourseService courseService = new CourseServiceImp();
        TaskService taskService = new TaskServiceImp();
        List<Course> courses = courseService.findCourseTea(user);
//        List<Task[]> taskNames = new ArrayList<Task[]>();
////        Map<Long, Task[]> map = new HashMap<Long, Task[]>();
////        for(int i = 0;i<courses.size();i++){
////            //查找并存储 所有课程下的所有作业名
////             map.put(courses.get(i).getCourseName(),taskService.findTaskNameTea(courses.get(i).getId()));
////        }
//        for(int i = 0;i<courses.size(); i++){
//            taskNames.set(i, taskService.findTaskNameTea(courses.get(i).getId()));
//
//        }
        List<String> taskNames = new ArrayList<>();
        for(int i= 0; i<courses.size();i++){
            for(int j = 0;j< taskService.findTaskNameTea(courses.get(i).getId()).size();j++){
                taskNames.add(i+j,taskService.findTaskNameTea(courses.get(i).getId()).get(j).getTaskName());
            }
        }
        ResultInfo resultInfo = new ResultInfo();
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();

        if (taskNames.size() == 0){
            resultInfo.setSuccess(false);
            resultInfo.setMessage("您当前没有发布作业");
        }else {
            resultInfo.setSuccess(true);
            resultInfo.setData(courses);
            resultInfo.setData2(taskNames);
        }
        try {
            mapper.writeValue(response.getWriter(),resultInfo);
        } catch (IOException e) {
            log.error("响应输出流出错");
        }
    }
}
