package com.wjc;

import com.wjc.pojo.Course;
import com.wjc.pojo.Task;

import java.util.List;

public interface TaskService {
    /**
     * 通过课程名查找所有练习
     * @param course
     * @return
     */
    List<Task> findTask(Course course);
}
