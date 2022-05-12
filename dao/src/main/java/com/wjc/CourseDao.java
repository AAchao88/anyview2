package com.wjc;

import com.wjc.pojo.Course;
import com.wjc.pojo.User;

import java.util.List;

public interface CourseDao {
    List<Course> findCourse(User user);
    Course findCourseInfo(String courseName);

//    List<Course> findCourseTea(User user);
}
