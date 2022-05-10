package com.wjc;

import com.wjc.imp.QuestionDaoImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionDaoTest {

    private QuestionDao questionDao;
    @Before
    public void setUp() throws Exception {
        questionDao = new QuestionDaoImp();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试QuestionDao");
    }

    @Test
    public void findQuestion() {
        assertEquals("a",questionDao.findQuestion("行列式","线性代数").get(0).getAnswer());
        System.out.println(questionDao.findQuestion("行列式","线性代数").get(0));
        assertNotNull(questionDao.findQuestion("行列式","线性代数"));
    }
}