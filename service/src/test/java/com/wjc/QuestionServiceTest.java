package com.wjc;

import com.wjc.imp.QuestionServiceImp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionServiceTest {

    private QuestionService questionService;
    @Before
    public void setUp() throws Exception {
        questionService = new QuestionServiceImp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findQuestion() {
        assertEquals("不知道",questionService.findQuestion("行列式","线性代数").get(0).getOptionA());
        System.out.println(questionService.findQuestion("行列式","线性代数").get(0));
    }
}