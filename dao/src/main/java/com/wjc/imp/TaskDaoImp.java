package com.wjc.imp;

import com.wjc.TaskDao;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.Tasktea;
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
    public int changeScore(Task task, long score,long completed,long status) {
        String sql = "update task set score = ?,completed = ?,status = ? where id =?";
        return CRUDUtil.executeUpdate(sql,score,completed,status,task.getId());
    }

    @Override
    public List<Tasktea> findTeaTask(User user) {
        String sql = "select * from tasktea where teacher_id = ?";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Tasktea.class),user.getId());
    }

    @Override
    public int addTask(Tasktea tasktea,User user,long course_id) {
        String sql = "insert into task (taskName,deadline,total,user_id,course_id)values(?,?,?,?,?)";
        return CRUDUtil.executeUpdate(sql,tasktea.getTaskName(),tasktea.getDeadline(),tasktea.getTotal(),user.getId(),course_id);
    }

    @Override
    public int deleteTask(User user, String taskName) {
        String sql = "delete from task where teacher_id = ? and taskName = ?";
        return CRUDUtil.executeUpdate(sql,user.getId(),taskName);
    }

    @Override
    public List<Task> getBatchTask(Tasktea tasktea) {
        String sql = "select * from task where taskName = ? and teacher_id = ?";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Task.class),tasktea.getTaskName(),tasktea.getTeacher_id());

    }

    @Override
    public int releaseTask(Task task) {
        String sql = "insert into task (taskName,teacher_id,course_id,user_id,score,total,deadline)values(?,?,?,?,?,?,?)";
        return CRUDUtil.executeUpdate(sql,task.getTaskName(),task.getTeacher_id(),task.getCourse_id(),task.getUser_id(),task.getScore(),task.getTotal(),task.getDeadline());

    }

    @Override
    public int extendTask(Task task) {
        String sql = "update task set deadline = ? where taskName = ? and user_id = ?";
        return CRUDUtil.executeUpdate(sql,task.getDeadline(),task.getTaskName(),task.getUser_id());
    }

}
