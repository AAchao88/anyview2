package com.wjc.imp;

import com.wjc.TaskDao;
import com.wjc.TaskService;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.Tasktea;
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

    @Override
    public Boolean changeScore(Task task, long score,long completed,long status) {
        TaskDao taskDao = new TaskDaoImp();
        return taskDao.changeScore(task,score,completed,status);
    }

    @Override
    public List<Tasktea> findTeaTask(User user) {
        TaskDao taskDao = new TaskDaoImp();
        return taskDao.findTeaTask(user);
    }

//    @Override
//    public List<Task> findTaskNameTea(long course_id) {
//        TaskDao taskDao = new TaskDaoImp();
//        return taskDao.findTaskNameTea(course_id);
//    }


}
