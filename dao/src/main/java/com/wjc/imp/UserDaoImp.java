package com.wjc.imp;

import com.wjc.UserDao;
import com.wjc.pojo.User;
import com.wjc.utils.BeanHandler;
import com.wjc.utils.BeanListHandler;
import com.wjc.utils.CRUDUtil;

import java.util.List;

public class UserDaoImp implements UserDao {

    @Override
    public User findByUserNumberAndPassword(User loginUser) {
        String sql = "select * from user where userNumber = ? and password = ?";
        return CRUDUtil.executeQuery(sql,new BeanHandler<>(User.class),loginUser.getUserNumber(),loginUser.getPassword());
    }

    @Override
    public User getPersonalInformation(User user) {
        String sql = "select * from user where userNumber = ?";
        return CRUDUtil.executeQuery(sql,new BeanHandler<>(User.class),user.getUserNumber());
    }

    @Override
    public List<User> getAllUserInClass(String className) {
        String sql = "select * from user where className = ?";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(User.class),className);
    }
}
