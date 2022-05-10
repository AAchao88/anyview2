package com.wjc.imp;

import com.wjc.QuestionDao;
import com.wjc.pojo.Question;
import com.wjc.utils.BeanListHandler;
import com.wjc.utils.CRUDUtil;

import java.util.List;

public class QuestionDaoImp implements QuestionDao {
    @Override
    public List<Question> findQuestion(String taskName,String courseName) {
        String sql = "select * from question where taskName = ? and courseName = ?";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Question.class),taskName,courseName);
    }
}
