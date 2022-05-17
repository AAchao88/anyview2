package com.wjc;

import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;

public interface TaskTeaService {
    Boolean deleteTaskTea(User user,String taskName);

    Boolean addTaskTea(Tasktea tasktea);

    Tasktea findTaskTea(String taskName,User user);

    Boolean changeTaskTea(Tasktea tasktea,long id);

    Boolean updateReleaseTime(Tasktea tasktea);

    Boolean endTaskTea(Tasktea tasktea);
}
