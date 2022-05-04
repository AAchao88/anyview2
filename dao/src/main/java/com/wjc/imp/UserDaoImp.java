package com.wjc.imp;

import com.wjc.UserDao;
import com.wjc.pojo.User;
import com.wjc.utils.BeanHandler;
import com.wjc.utils.CRUDUtil;

public class UserDaoImp implements UserDao {

    @Override
    public User findByUserNumberAndPassword(User loginUser) {
        String sql = "select * from user where userNumber = ? and password = ?";
        return CRUDUtil.executeQuery(sql,new BeanHandler<>(User.class),loginUser.getUserNumber(),loginUser.getPassword());
    }
}
