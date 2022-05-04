package com.wjc.imp;

import com.wjc.UserDao;
import com.wjc.UserService;
import com.wjc.pojo.User;

public class UserServiceImp implements UserService {

    @Override
    public User login(User loginUser) {
        UserDao userDao = new UserDaoImp();
        return userDao.findByUserNumberAndPassword(loginUser);
    }
}
