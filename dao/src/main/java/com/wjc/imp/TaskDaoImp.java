package com.wjc.imp;

import com.wjc.TaskDao;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.User;
import com.wjc.utils.BeanListHandler;
import com.wjc.utils.CRUDUtil;

import java.util.List;

public class TaskDaoImp implements TaskDao {

    @Override
    public List<Task> findTask(Course course, User user) {
        String sql = "select * from task where course_id = ? and user_id = ?";
        System.out.println(course.getId());
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Task.class),course.getId(),user.getId());
    }
}
