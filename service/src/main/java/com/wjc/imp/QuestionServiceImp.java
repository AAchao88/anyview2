package com.wjc.imp;

import com.wjc.QuestionDao;
import com.wjc.QuestionService;
import com.wjc.pojo.Question;

import java.util.List;

public class QuestionServiceImp implements QuestionService {
    @Override
    public List<Question> findQuestion(String taskName,String courseName) {
        QuestionDao questionDao = new QuestionDaoImp();
        return questionDao.findQuestion(taskName,courseName);
    }
}
