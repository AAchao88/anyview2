package com.wjc;

import com.wjc.imp.TaskTeaServiceImp;
import com.wjc.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
}