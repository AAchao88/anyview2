package com.wjc;

import com.wjc.pojo.User;

public interface UserDao {
    User findByUserNumberAndPassword(User loginUser);
    User getPersonalInformation(User user);
}
