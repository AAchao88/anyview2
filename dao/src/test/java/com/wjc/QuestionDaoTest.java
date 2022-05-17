package com.wjc;

import com.wjc.imp.QuestionDaoImp;
import com.wjc.pojo.Question;
import com.wjc.pojo.Tasktea;
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

    @Test
    public void addQuestion() {
        Tasktea tasktea = new Tasktea();
        Question question = new Question();
        tasktea.setTaskName("新青年");
        tasktea.setCourseName("近代史");
        question.setQuestionName("关于新青年的理解");
        question.setQuestionContent("什么是新青年");
        question.setType(1);
        question.setScore(10);
        question.setAnswer("a");
        assertEquals(1,questionDao.addQuestion(tasktea,question));
    }

    @Test
    public void changeQuestion() {
        Tasktea tasktea = new Tasktea();
        tasktea.setTaskName("行列式");
        tasktea.setCourseName("线性代数");
        Question question = new Question();
        question.setId(1);
        question.setScore(30);
        assertEquals(1,questionDao.changeQuestion(tasktea,question));
    }

    @Test
    public void findQuestionById() {
        long id = 7;
        assertEquals("甲午战争的背景",questionDao.findQuestionById(id).getQuestionContent());
    }

    @Test
    public void getAllQuestion() {
//        System.out.println(questionDao.getAllQuestion());
        assertEquals(7,questionDao.getAllQuestion().size());
    }

    @Test
    public void deleteQuestionByManager() {
        long question_id = 8;
        assertEquals(1,questionDao.deleteQuestionByManager(question_id));
    }
}