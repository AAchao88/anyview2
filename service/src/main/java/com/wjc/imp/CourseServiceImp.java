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
}
