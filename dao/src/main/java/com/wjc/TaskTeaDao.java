package com.wjc;

import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;

public interface TaskTeaDao {

    int deleteTaskTea(User user,String taskName);
    int addTaskTea(Tasktea tasktea);

    Tasktea findTaskTea(String taskName,User user);

    int changeTaskTea(Tasktea tasktea,long id);

    int updaterReleaseTime(Tasktea tasktea);
}
