package com.wjc;

import com.wjc.pojo.User;

public interface UserService {
    User login(User loginUser);
    User getPersonalInformation(User user);
}
