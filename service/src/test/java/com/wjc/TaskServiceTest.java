package com.wjc;

import com.wjc.imp.TaskServiceImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

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

//    @Test
//    public void findTaskNameTea() {
//        Course course = new Course();
//        course.setId(2);
//        assertEquals(4,taskService.findTaskNameTea(2).size());
//    }


    @Test
    public void findTeaTask() {
        User user = new User();
        user.setId(1);
        assertEquals("行列式",taskService.findTeaTask(user).get(0).getTaskName());
    }

    @Test
    public void addTask() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTaskName("线性代数的理论架构");
        tasktea.setDeadline(new Timestamp(165234590));
        tasktea.setTotal(12);
        User user = new User();
        user.setId(2);
        assertTrue(taskService.addTask(tasktea,user,2));
    }

    @Test
    public void getBatchTask() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("行列式");
        for(Task task:taskService.getBatchTask(tasktea)){
            System.out.println(task);
        }
        assertEquals("行列式",taskService.getBatchTask(tasktea).get(0).getTaskName());
    }

    @Test
    public void releaseTask() {
        Task task = new Task();
        task.setUser_id(2);
        task.setCourse_id(2);
        task.setTaskName("抗日");
        task.setScore(20);
        task.setTotal(10);
        task.setTeacher_id(1);
        task.setDeadline(new Timestamp(654321294));
        assertTrue(taskService.releaseTask(task));
    }
}