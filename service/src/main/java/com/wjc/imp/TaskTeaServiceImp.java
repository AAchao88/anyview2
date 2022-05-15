package com.wjc.imp;

import com.wjc.TaskTeaDao;
import com.wjc.TaskTeaService;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;

public class TaskTeaServiceImp implements TaskTeaService {
    @Override
    public Boolean deleteTaskTea(User user, String taskName) {
        TaskTeaDao taskTeaDao = new TaskTeaDaoImp();
        if(taskTeaDao.deleteTaskTea(user,taskName) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean addTaskTea(Tasktea tasktea) {
        TaskTeaDao taskTeaDao = new TaskTeaDaoImp();
        if (taskTeaDao.addTaskTea(tasktea) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Tasktea findTaskTea(String taskName, User user) {
        TaskTeaDao taskTeaDao = new TaskTeaDaoImp();
        return taskTeaDao.findTaskTea(taskName,user);
    }

    @Override
    public Boolean changeTaskTea(Tasktea tasktea, long id) {
        TaskTeaDao taskTeaDao = new TaskTeaDaoImp();
        if (taskTeaDao.changeTaskTea(tasktea,id) == 1){
            return  true;
        }else {
            return  false;
        }
    }
}
