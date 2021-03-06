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
        if(taskDao.changeScore(task,score,completed,status) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Tasktea> findTeaTask(User user) {
        TaskDao taskDao = new TaskDaoImp();
        return taskDao.findTeaTask(user);
    }

    @Override
    public Boolean addTask(Tasktea tasktea, User user, long course_id) {
        TaskDao taskDao = new TaskDaoImp();
        if(taskDao.addTask(tasktea,user,course_id) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean deleteTask(User user, String taskName) {
        TaskDao taskDao = new TaskDaoImp();
//        String sql = "delete from task where teacher_id = ? and taskName = ?";
        if(taskDao.deleteTask(user,taskName) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Task> getBatchTask(Tasktea tasktea) {
        TaskDao taskDao = new TaskDaoImp();
        return taskDao.getBatchTask(tasktea);

    }

    @Override
    public Boolean releaseTask(Task task) {
        TaskDao taskDao = new TaskDaoImp();
        if (taskDao.releaseTask(task) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean extendTask(Task task) {
        TaskDao taskDao = new TaskDaoImp();
        if (taskDao.extendTask(task) == 1){
            return true;
        }else {
            return false;
        }
    }


}
