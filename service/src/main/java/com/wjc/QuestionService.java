package com.wjc;

import com.wjc.pojo.Question;
import com.wjc.pojo.Tasktea;

import java.util.List;

public interface QuestionService {
    List<Question> findQuestion(String taskName,String courseName);

    Boolean addQuestion(Tasktea tasktea,Question question);

    Boolean changeQuestion(Tasktea tasktea,Question question);
}
