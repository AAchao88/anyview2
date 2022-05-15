package com.wjc;

import com.wjc.imp.TaskDaoImp;
import com.wjc.pojo.Course;
import com.wjc.pojo.Task;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

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
        User user = new User();
        user.setId(2);
        for(Task task:taskDao.findTask(course,user)){
            System.out.println(task);
        }
        assertEquals("行列式",taskDao.findTask(course,user).get(0).getTaskName());
    }

    @Test
    public void getTask() {
        Course course = new Course();
        course.setId(2);
        User user = new User();
        user.setId(2);
        String taskName = "行列式";
        assertEquals(0,taskDao.getTask(course,user,taskName).getScore());
        System.out.println(taskDao.getTask(course,user,taskName));
    }

    @Test
    public void changeScore() {
        Task task = new Task();
        task.setId(1);
        long score = 20;
        long completed = 2;
        long status = 1;
        assertEquals(1,taskDao.changeScore(task,score,completed,status));
    }

//    @Test
//    public void findTaskNameTea() {
//        Course course = new Course();
//        course.setId(2);
//        assertEquals("行列式",taskDao.findTaskNameTea(course.getId()).get(0).getTaskName());
//        for(int i =0 ;i<4;i++){
//            System.out.println(taskDao.findTaskNameTea(2).get(i).getTaskName());
//        }
//    }


    @Test
    public void findTeaTask() {
        User user = new User();
        user.setId(1);
        assertEquals("行列式",taskDao.findTeaTask(user).get(0).getTaskName());
    }

    @Test
    public void addTask() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTaskName("线性代数的应用");
        tasktea.setDeadline(new Timestamp(1652345678));
        tasktea.setTotal(23);
        User user = new User();
        user.setId(2);
//        Course course = new Course();
//        course.setId(2);
        assertEquals(1,taskDao.addTask(tasktea,user,2));
    }

    @Test
    public void deleteTask() {
        User user = new User();
        user.setId(1);
        String taskName = "线性代数的应用";
        assertEquals(1,taskDao.deleteTask(user,taskName));
    }

    @Test
    public void getBatchTask() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("行列式");
        for(Task task:taskDao.getBatchTask(tasktea)){
            System.out.println(task);
        }
        assertEquals("行列式",taskDao.getBatchTask(tasktea).get(0).getTaskName());
    }
}