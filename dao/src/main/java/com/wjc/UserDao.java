package com.wjc;

import com.wjc.pojo.User;

import java.util.List;

public interface UserDao {
    User findByUserNumberAndPassword(User loginUser);
    User getPersonalInformation(User user);

    /**
     * 通过班级名获取所有该班级的学生信息
     * @param className
     * @return
     */
    List<User> getAllUserInClass(String className);
}
