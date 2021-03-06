package com.wjc.imp;

import com.wjc.QuestionDao;
import com.wjc.pojo.Question;
import com.wjc.pojo.Tasktea;
import com.wjc.utils.BeanHandler;
import com.wjc.utils.BeanListHandler;
import com.wjc.utils.CRUDUtil;

import java.util.List;

public class QuestionDaoImp implements QuestionDao {
    @Override
    public List<Question> findQuestion(String taskName,String courseName) {
        String sql = "select * from question where taskName = ? and courseName = ?";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Question.class),taskName,courseName);
    }

    @Override
    public int addQuestion(Tasktea tasktea,Question question) {
        String sql = "insert into question (taskName,courseName,questionName,questionContent,type,score,answer)values(?,?,?,?,?,?,?)";
        return CRUDUtil.executeUpdate(sql,tasktea.getTaskName(),tasktea.getCourseName(),question.getQuestionName(),question.getQuestionContent(),question.getType(),question.getScore(),question.getAnswer());
    }

    @Override
    public int changeQuestion(Tasktea tasktea, Question question) {
        String sql = "update question set taskName = ?,courseName = ?,score = ? where id = ?";
        return CRUDUtil.executeUpdate(sql,tasktea.getTaskName(),tasktea.getCourseName(),question.getScore(),question.getId());
    }

    @Override
    public Question findQuestionById(long id) {
        String sql = "select * from question where id = ?";
        return CRUDUtil.executeQuery(sql,new BeanHandler<>(Question.class),id);

    }

    @Override
    public List<Question> getAllQuestion() {
        String sql = "select * from question";
        return CRUDUtil.executeQuery(sql,new BeanListHandler<>(Question.class));
    }

    @Override
    public int deleteQuestionByManager(long question_id) {
        String sql = "delete from question where id = ?";
        return CRUDUtil.executeUpdate(sql,question_id);
    }
}
