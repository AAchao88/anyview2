package com.wjc.imp;

import com.wjc.UserDao;
import com.wjc.UserService;
import com.wjc.pojo.User;

import java.util.List;

public class UserServiceImp implements UserService {

    @Override
    public User login(User loginUser) {
        UserDao userDao = new UserDaoImp();
        return userDao.findByUserNumberAndPassword(loginUser);
    }

    @Override
    public User getPersonalInformation(User user) {
        UserDao userDao = new UserDaoImp();
        return userDao.getPersonalInformation(user);
    }

    @Override
    public List<User> getAllUserInClass(String className) {
        UserDao userDao = new UserDaoImp();
        return userDao.getAllUserInClass(className);
    }

    @Override
    public User findUserById(long user_id) {
        UserDao userDao = new UserDaoImp();
        return userDao.findUserById(user_id);
    }
}
