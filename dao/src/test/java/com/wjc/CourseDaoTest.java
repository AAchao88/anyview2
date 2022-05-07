package com.wjc;

import com.wjc.imp.CourseDaoImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.User;
import com.wjc.utils.BeanListHandler;
import com.wjc.utils.CRUDUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CourseDaoTest {
    private User user;
    private CourseDao courseDao;
    private String sql;
    @Before
    public void setUp() throws Exception {
        user = new User();
        courseDao = new CourseDaoImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试CourseDao");
    }

    @Test
    public void findCourse() {
        sql = "select * from course where className = ?";
        user.setClassName("21级计算机类7班");
        List<Course> courseList = CRUDUtil.executeQuery(sql,new BeanListHandler<Course>(Course.class),user.getClassName());
        System.out.println(courseList.size());
        assertEquals("线性代数",courseList.get(1).getCourseName());

    }
}