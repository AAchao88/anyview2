package com.wjc;

import com.wjc.imp.UserServiceImp;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;
    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试UserServiceImp");
    }

    @Test
    public void getPersonalInformation() {
        User user = new User();
        user.setUserNumber("123456");
        assertNotNull(userService.getPersonalInformation(user));
        System.out.println(userService.getPersonalInformation(user));
    }

    @Test
    public void login() {
        User user = new User();
        user.setUserNumber("1234561");
        user.setPassword("1234561");
        assertNull(userService.login(user));
    }

    @Test
    public void getAllUserInClass() {
        String className = "21级计算机类7班";
        assertEquals(2,userService.getAllUserInClass(className).size());
    }
}