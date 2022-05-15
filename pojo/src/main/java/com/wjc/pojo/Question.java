package com.wjc.pojo;


public class Question {

  private long id;
  private String questionName;
  private String taskName;
  private String courseName;
  private String questionContent;
  private String answer;
  private int type;
  private long score;
  private long batched;
  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;

  public long getBatched() {
    return batched;
  }

  public void setBatched(long batched) {
    this.batched = batched;
  }

  public String getOptionA() {
    return optionA;
  }

  public void setOptionA(String optionA) {
    this.optionA = optionA;
  }

  public String getOptionB() {
    return optionB;
  }

  public void setOptionB(String optionB) {
    this.optionB = optionB;
  }

  public String getOptionC() {
    return optionC;
  }

  public void setOptionC(String optionC) {
    this.optionC = optionC;
  }

  public String getOptionD() {
    return optionD;
  }

  public void setOptionD(String optionD) {
    this.optionD = optionD;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

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

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
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

  @Override
  public String toString() {
    return "Question{" +
            "id=" + id +
            ", questionName='" + questionName + '\'' +
            ", taskName='" + taskName + '\'' +
            ", courseName='" + courseName + '\'' +
            ", questionContent='" + questionContent + '\'' +
            ", answer='" + answer + '\'' +
            ", type=" + type +
            ", score=" + score +
            ", batched=" + batched +
            ", optionA='" + optionA + '\'' +
            ", optionB='" + optionB + '\'' +
            ", optionC='" + optionC + '\'' +
            ", optionD='" + optionD + '\'' +
            '}';
  }
}
