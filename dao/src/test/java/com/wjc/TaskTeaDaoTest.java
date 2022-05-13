package com.wjc;

import com.wjc.imp.TaskTeaDaoImp;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        assertTrue(taskTeaDao.deleteTaskTea(user,taskName));
  }
}