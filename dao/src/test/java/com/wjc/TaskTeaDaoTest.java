package com.wjc;

import com.wjc.imp.TaskTeaDaoImp;
import com.wjc.pojo.Tasktea;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class TaskTeaDaoTest {

    private TaskTeaDao taskTeaDao;
    @After
    public void tearDown() throws Exception {
        System.out.println("测试TaskTeaDao");
    }

    @Before
    public void setUp() throws Exception {
        taskTeaDao = new TaskTeaDaoImp();
    }

    @Test
    public void deleteTaskTea() {
        User user = new User();
        user.setId(1);
        String taskName = "线性代数的应用";
        assertEquals(1,taskTeaDao.deleteTaskTea(user,taskName));
  }

    @Test
    public void addTaskTea() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("新青年2");
        tasktea.setTotal(10);
        tasktea.setScore(100);
        tasktea.setClassName("21级计算机类7班");
        tasktea.setCourseName("近代史");
        assertEquals(1,taskTeaDao.addTaskTea(tasktea));
    }

    @Test
    public void findTaskTea() {
        User user = new User();
        user.setId(1);
        String taskName = "甲午战争";
        System.out.println(taskTeaDao.findTaskTea(taskName,user).getClassName());
        assertEquals(15,taskTeaDao.findTaskTea(taskName,user).getTotal());
        assertEquals(5,taskTeaDao.findTaskTea(taskName,user).getId());
    }

    @Test
    public void changeTaskTea() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("新青年3");
        tasktea.setTotal(10);
        tasktea.setScore(90);
        tasktea.setClassName("21级计算机类7班");
        tasktea.setCourseName("近代史");
        assertEquals(1,taskTeaDao.changeTaskTea(tasktea,4));

    }

    @Test
    public void updateReleaseTime() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTeacher_id(1);
        tasktea.setTaskName("甲午战争");
        tasktea.setReleaseTime(new Timestamp(53432345));
        tasktea.setDeadline(new Timestamp(54399569));
        assertEquals(1,taskTeaDao.updaterReleaseTime(tasktea));
    }
}