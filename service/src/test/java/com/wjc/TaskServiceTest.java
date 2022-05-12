package com.wjc;

import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void getTask() {
        Course course = new Course();
        course.setId(2);
        User user = new User();
        user.setId(2);
        String taskName = "行列式";
        assertEquals(0,taskService.getTask(course,user,taskName).getScore());
    }

    @Test
    public void changeScore() {
        Task task = new Task();
        task.setId(1);
        long score = 30;
        long completed = 12;
        long status = 0;
        assertTrue(taskService.changeScore(task,score,completed,status));
    }

    @Test
    public void findTaskNameTea() {
        Course course = new Course();
        course.setId(2);
        assertEquals(4,taskService.findTaskNameTea(2).size());
    }
}