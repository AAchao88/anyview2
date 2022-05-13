package com.wjc.imp;

import com.wjc.TaskTeaDao;
import com.wjc.TaskTeaService;
import com.wjc.pojo.User;

public class TaskTeaServiceImp implements TaskTeaService {
    @Override
    public Boolean deleteTaskTea(User user, String taskName) {
        TaskTeaDao taskTeaDao = new TaskTeaDaoImp();
        return taskTeaDao.deleteTaskTea(user,taskName);
    }
}
