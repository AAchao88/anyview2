package com.wjc;

import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.User;

import java.util.List;

public interface TaskDao {

    /**
     * 通过课程名查找所有练习
     * @param course
     * @return
     */
    List<Task> findTask(Course course, User user);

    /**
     * 通过课程，作业名，user查找特定作业
     * @param course
     * @param user
     * @param taskName
     * @return
     */
    Task getTask(Course course,User user,String taskName);

    Boolean changeScore(Task task,long score,long completed,long status);

    /**
     *  教师根据course_id查询所有作业名（去重）
     * @param course_id
     * @return
     */
    List<Task> findTaskNameTea(long course_id);

}
