package com.wjc;

import com.wjc.imp.TaskTeaServiceImp;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

public class TaskTeaServiceTest {

    private TaskTeaService taskTeaService;
    @Before
    public void setUp() throws Exception {
        taskTeaService = new TaskTeaServiceImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试TaskTeaService");
    }

    @Test
    public void deleteTaskTea() {
        User user = new User();
        user.setId(1);
        String taskName = "线性代数的应用";
        assertTrue(taskTeaService.deleteTaskTea(user,taskName));
    }

    @Test
    public void addTaskTea() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("甲午战争");
        tasktea.setTotal(15);
        tasktea.setClassName("21级计算机类7班");
        tasktea.setCourseName("近代史");
        assertTrue(taskTeaService.addTaskTea(tasktea));
    }

    @Test
    public void findTaskTea() {
        User user = new User();
        user.setId(1);
        String taskName = "甲午战争";
        assertEquals(15,taskTeaService.findTaskTea(taskName,user).getTotal());
    }

    @Test
    public void changeTaskTea() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("新青年1");
        tasktea.setTotal(10);
        tasktea.setScore(900);
        tasktea.setClassName("21级计算机类7班");
        tasktea.setCourseName("近代史");
        assertTrue(taskTeaService.changeTaskTea(tasktea,4));
    }

    @Test
    public void updateReleaseTime() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("甲午战争");
        tasktea.setReleaseTime(new Timestamp(534323453));
        tasktea.setDeadline(new Timestamp(543995699));
        assertTrue(taskTeaService.updateReleaseTime(tasktea));
    }

    @Test
    public void endTaskTea() {
        Tasktea tasktea = new Tasktea();
        tasktea.setStatus(0);
        tasktea.setTaskName("抗日");
        tasktea.setTeacher_id(1);
        assertTrue(taskTeaService.endTaskTea(tasktea));
    }
}