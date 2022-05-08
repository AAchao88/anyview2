package com.wjc.imp;

import com.wjc.TaskDao;
import com.wjc.TaskService;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;

import java.util.List;

public class TaskServiceImp implements TaskService {

    @Override
    public List<Task> findTask(Course course) {
        TaskDao taskDao = new TaskDaoImp();
        return taskDao.findTask(course);
    }
}
