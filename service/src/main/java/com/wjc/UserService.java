package com.wjc;

import com.wjc.pojo.User;

import java.util.List;

public interface UserService {
    User login(User loginUser);
    User getPersonalInformation(User user);

    List<User> getAllUserInClass(String className);
}
