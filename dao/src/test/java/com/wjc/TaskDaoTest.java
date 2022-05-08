package com.wjc;

import com.wjc.imp.TaskDaoImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TaskDaoTest {

    private TaskDao taskDao;
    @Before
    public void setUp() throws Exception {
        taskDao = new TaskDaoImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试TaskDao");
    }

    @Test
    public void findTask() {
        Course course = new Course();
        course.setId(2);
        for(Task task:taskDao.findTask(course)){
            System.out.println(task);
        }
        assertEquals("行列式",taskDao.findTask(course).get(0).getTaskName());
    }
}