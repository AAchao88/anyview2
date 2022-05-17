package com.wjc;

import com.wjc.pojo.Question;
import com.wjc.pojo.Tasktea;

import java.util.List;

public interface QuestionDao {
    List<Question> findQuestion(String taskName,String courseName);
    int addQuestion(Tasktea tasktea,Question question);

    int changeQuestion(Tasktea tasktea,Question question);

    Question findQuestionById(long id);

    List<Question> getAllQuestion();

    int deleteQuestionByManager(long question_id);
}
