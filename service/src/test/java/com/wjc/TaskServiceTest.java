package com.wjc;

import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskServiceTest {

    private TaskService taskService;
    @Before
    public void setUp() throws Exception {
        taskService = new TaskServiceImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试TaskService");
    }

    @Test
    public void findTask() {
        User user = new User();
        Course course = new Course();
        user.setId(2);
        course.setId(2);
        assertEquals("行列式",taskService.findTask(course,user).get(0).getTaskName());
    }
}