package com.wjc;

import com.wjc.pojo.Course;
import com.wjc.pojo.User;

import java.util.List;

public interface CourseService {
    List<Course> findCourse(User user);
}
