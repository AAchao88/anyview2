package com.wjc.imp;

import com.wjc.TaskDao;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.User;
import com.wjc.utils.BeanHandler;
import com.wjc.utils.BeanListHandler;
import com.wjc.utils.CRUDUtil;

import java.util.List;

public class TaskDaoImp implements TaskDao {

    @Override
    public List<Task> findTask(Course course, User user) {
        String sql = "select * from task where course_id = ? and user_id = ?";

        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Task.class),course.getId(),user.getId());
    }

    @Override
    public Task getTask(Course course, User user, String taskName) {
        String sql = "select * from task where course_id = ? and user_id = ? and taskName = ?";
        return CRUDUtil.executeQuery(sql,new BeanHandler<>(Task.class),course.getId(),user.getId(),taskName);

    }

    @Override
    public Boolean changeScore(Task task, long score,long completed,long status) {
        String sql = "update task set score = ?,completed = ?,status = ? where id =?";
        if(CRUDUtil.executeUpdate(sql,score,completed,status,task.getId()) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Task> findTaskNameTea(long course_id) {
        String sql = "select  distinct taskName from task where course_id = ?";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Task.class),course_id);
    }


}
