package com.wjc;

import com.wjc.pojo.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findQuestion(String taskName,String courseName);
}
