package com.wjc.imp;

import com.wjc.TaskTeaDao;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;
import com.wjc.utils.BeanHandler;
import com.wjc.utils.CRUDUtil;

public class TaskTeaDaoImp implements TaskTeaDao {
    @Override
    public int deleteTaskTea(User user, String taskName) {
        String sql = "delete from tasktea where teacher_id = ? and taskName = ?";
        return CRUDUtil.executeUpdate(sql,user.getId(),taskName);
    }

    @Override
    public int addTaskTea(Tasktea tasktea) {
        String sql = "insert into tasktea (teacher_id,taskName,courseName,className,total,score)values(?,?,?,?,?,?)";
        return CRUDUtil.executeUpdate(sql,tasktea.getTeacher_id(),tasktea.getTaskName(),tasktea.getCourseName(),tasktea.getClassName(),tasktea.getTotal(),tasktea.getScore());
    }

    @Override
    public Tasktea findTaskTea(String taskName, User user) {
        String sql = "select id,taskName,courseName,className,total,score from tasktea where taskName = ? and teacher_id = ?";
        return CRUDUtil.executeQuery(sql,new BeanHandler<>(Tasktea.class),taskName,user.getId());
    }

    @Override
    public int changeTaskTea(Tasktea tasktea, long id) {
        String sql = "update tasktea set teacher_id =?,taskName =?,courseName =?,className =?,total=?,score=? where id = ?";
        return CRUDUtil.executeUpdate(sql,tasktea.getTeacher_id(),tasktea.getTaskName(),tasktea.getCourseName(),tasktea.getClassName(),tasktea.getTotal(),tasktea.getScore(),id);
    }

    @Override
    public int updaterReleaseTime(Tasktea tasktea) {
        String sql = "update tasktea set releaseTime = ?,deadline = ? where taskName = ? and teacher_id =?";
        return CRUDUtil.executeUpdate(sql,tasktea.getReleaseTime(),tasktea.getDeadline(),tasktea.getTaskName(),tasktea.getTeacher_id());
    }

    @Override
    public int endTaskTea(Tasktea tasktea) {
        String sql = "update tasktea set status = ? where taskName = ? and teacher_id =?";
        return CRUDUtil.executeUpdate(sql,tasktea.getStatus(),tasktea.getTaskName(),tasktea.getTeacher_id());

    }
}
