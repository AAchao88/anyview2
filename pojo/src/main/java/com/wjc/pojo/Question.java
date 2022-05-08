package com.wjc.pojo;


public class Question {

  private long id;
  private String questionName;
  private long task_id;
  private String questionContent;
  private String answer;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getQuestionName() {
    return questionName;
  }

  public void setQuestionName(String questionName) {
    this.questionName = questionName;
  }

  public long getTask_id() {
    return task_id;
  }

  public void setTask_id(long task_id) {
    this.task_id = task_id;
  }

  public String getQuestionContent() {
    return questionContent;
  }

  public void setQuestionContent(String questionContent) {
    this.questionContent = questionContent;
  }


  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

}
