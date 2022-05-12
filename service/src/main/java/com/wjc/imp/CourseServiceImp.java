package com.wjc.imp;

import com.wjc.CourseDao;
import com.wjc.CourseService;
import com.wjc.pojo.Course;
import com.wjc.pojo.User;

import java.util.List;

public class CourseServiceImp implements CourseService {

    @Override
    public List<Course> findCourse(User user) {
        CourseDao courseDao = new CourseDaoImp();
        return courseDao.findCourse(user);
    }

    @Override
    public Course findCourseInfo(String courseName) {
        CourseDao courseDao = new CourseDaoImp();
        return courseDao.findCourseInfo(courseName);
    }

    @Override
    public List<Course> findCourseTea(User user) {
        CourseDao courseDao = new CourseDaoImp();
        return courseDao.findCourseTea(user);
    }
}
