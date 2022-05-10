package com.wjc.imp;

import com.wjc.TaskDao;
import com.wjc.TaskService;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.User;

import java.util.List;

public class TaskServiceImp implements TaskService {

    @Override
    public List<Task> findTask(Course course, User user) {
        TaskDao taskDao = new TaskDaoImp();
        return taskDao.findTask(course,user);
    }

    @Override
    public Task getTask(Course course, User user, String taskName) {
        TaskDao taskDao = new TaskDaoImp();
        return taskDao.getTask(course,user,taskName);
    }
}
