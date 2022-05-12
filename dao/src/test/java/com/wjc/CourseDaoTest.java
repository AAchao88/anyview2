package com.wjc;

import com.wjc.imp.CourseDaoImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CourseDaoTest {

    private CourseDao courseDao;
    @Before
    public void setUp() throws Exception {
        courseDao = new CourseDaoImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试CourseDao");
    }

    @Test
    public void findCourse() {
        User user = new User();
        user.setClassName("21级计算机类7班");
        List<Course> courseList = courseDao.findCourse(user);
        System.out.println(courseList.size());
        assertEquals("线性代数",courseList.get(1).getCourseName());
        System.out.println(courseList.get(0));
    }

    @Test
    public void findCourseInfo() {
        String courseName = "线性代数";
        Course course = courseDao.findCourseInfo(courseName);
        assertEquals(2,course.getId());
        System.out.println(courseDao.findCourseInfo(courseName));
    }

//    @Test
//    public void findCourseTea() {
//        User user = new User();
//        user.setId(1);
//        assertEquals(3,courseDao.findCourseTea(user).size());
//    }
}