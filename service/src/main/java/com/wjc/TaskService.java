package com.wjc;

import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;

import java.util.List;

public interface TaskService {
    /**
     * 通过课程名查找所有练习
     * @param course
     * @return
     */
    List<Task> findTask(Course course, User user);

    Task getTask(Course course,User user,String taskName);

    Boolean changeScore(Task task,long score,long completed,long status);

//    List<Task> findTaskNameTea(long course_id);

    List<Tasktea> findTeaTask(User user);

    Boolean addTask(Tasktea tasktea,User user,long course_id);

    Boolean deleteTask(User user,String taskName);

    List<Task> getBatchTask(Tasktea tasktea);
}
