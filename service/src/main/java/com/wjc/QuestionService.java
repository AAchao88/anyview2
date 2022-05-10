package com.wjc;

import com.wjc.pojo.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findQuestion(String taskName,String courseName);
}
