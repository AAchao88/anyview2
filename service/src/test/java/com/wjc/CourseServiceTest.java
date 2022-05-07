package com.wjc;

import com.wjc.imp.CourseServiceImp;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseServiceTest {

    private User user;
    private CourseService courseService;
    @Before
    public void setUp() throws Exception {
        user = new User();
        courseService =  new CourseServiceImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试CourseService");
    }

    @Test
    public void findCourse() {
        user.setClassName("21级计算机类7班");
        assertEquals("线性代数",courseService.findCourse(user).get(1).getCourseName());
    }
}