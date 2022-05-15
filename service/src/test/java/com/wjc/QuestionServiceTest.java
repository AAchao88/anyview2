package com.wjc;

import com.wjc.imp.QuestionServiceImp;
import com.wjc.pojo.Question;
import com.wjc.pojo.Tasktea;
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

    @Test
    public void addQuestion() {
        Tasktea tasktea = new Tasktea();
        Question question = new Question();
        tasktea.setTaskName("甲午战争");
        tasktea.setCourseName("近代史");
        question.setQuestionName("关于甲午战争的理解");
        question.setQuestionContent("甲午战争的背景");
        question.setType(1);
        question.setScore(10);
        question.setAnswer("b");
        assertTrue(questionService.addQuestion(tasktea,question));
    }

    @Test
    public void changeQuestion() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTaskName("新青年10000");
        tasktea.setCourseName("近代史");
        tasktea.setScore(299);
        Question question = new Question();
        question.setId(6);
        assertTrue(questionService.changeQuestion(tasktea,question));
    }
}