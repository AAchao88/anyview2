package com.wjc;

import com.wjc.imp.CourseServiceImp;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseServiceTest {

    private CourseService courseService;
    @Before
    public void setUp() throws Exception {
        courseService =  new CourseServiceImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试CourseService");
    }

    @Test
    public void findCourse() {
        User user = new User();
        user.setClassName("21级计算机类7");
//        assertEquals("线性代数",courseService.findCourse(user).get(1).getCourseName());
        assertEquals(0,courseService.findCourse(user).size());
    }

    @Test
    public void findCourseInfo() {
        String courseName = "离散数学";
        assertEquals(1,courseService.findCourseInfo(courseName).getId());
    }

    @Test
    public void findCourseTea() {
        User user = new User();
        user.setId(1);
        assertEquals(3,courseService.findCourseTea(user).size());
    }
}