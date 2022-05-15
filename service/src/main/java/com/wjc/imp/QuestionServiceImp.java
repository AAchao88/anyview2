package com.wjc.imp;

import com.wjc.QuestionDao;
import com.wjc.QuestionService;
import com.wjc.pojo.Question;
import com.wjc.pojo.Tasktea;

import java.util.List;

public class QuestionServiceImp implements QuestionService {
    @Override
    public List<Question> findQuestion(String taskName,String courseName) {
        QuestionDao questionDao = new QuestionDaoImp();
        return questionDao.findQuestion(taskName,courseName);
    }

    @Override
    public Boolean addQuestion(Tasktea tasktea, Question question) {
        QuestionDao questionDao = new QuestionDaoImp();
        if (questionDao.addQuestion(tasktea,question) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean changeQuestion(Tasktea tasktea, Question question) {
        QuestionDao questionDao = new QuestionDaoImp();
        if (questionDao.changeQuestion(tasktea,question) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Question findQuestionById(long id) {
        QuestionDao questionDao = new QuestionDaoImp();
        return questionDao.findQuestionById(id);
    }
}
