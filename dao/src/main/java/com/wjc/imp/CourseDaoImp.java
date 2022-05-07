package com.wjc.imp;

import com.wjc.CourseDao;
import com.wjc.pojo.Course;
import com.wjc.pojo.User;
import com.wjc.utils.BeanListHandler;
import com.wjc.utils.CRUDUtil;

import java.util.List;

public class CourseDaoImp implements CourseDao {

    @Override
    public List<Course> findCourse(User user) {
        String sql = "select * from course where className = ?";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Course.class),user.getClassName());
    }
}
