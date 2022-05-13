package com.wjc.imp;

import com.wjc.TaskTeaDao;
import com.wjc.pojo.User;
import com.wjc.utils.CRUDUtil;

public class TaskTeaDaoImp implements TaskTeaDao {
    @Override
    public Boolean deleteTaskTea(User user, String taskName) {
        String sql = "delete from tasktea where teacher_id = ? and taskName = ?";
        if(CRUDUtil.executeUpdate(sql,user.getId(),taskName) == 1){
            return true;
        }else {
            return false;
        }
    }
}
