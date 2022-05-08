package com.wjc.pojo;


import java.sql.Timestamp;

public class Task {

  private long id;
  private String taskName;
  private long course_id;
  private long user_id;
  private long score;
  private long total;
  private long completed;
  private java.sql.Timestamp deadline;
  private long status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public long getCourse_id() {
    return course_id;
  }

  public void setCourse_id(long course_id) {
    this.course_id = course_id;
  }

  public long getUser_id() {
    return user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
  }

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public long getCompleted() {
    return completed;
  }

  public void setCompleted(long completed) {
    this.completed = completed;
  }

  public Timestamp getDeadline() {
    return deadline;
  }

  public void setDeadline(Timestamp deadline) {
    this.deadline = deadline;
  }

  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Task{" +
            "id=" + id +
            ", taskName='" + taskName + '\'' +
            ", course_id=" + course_id +
            ", user_id=" + user_id +
            ", score=" + score +
            ", total=" + total +
            ", completed=" + completed +
            ", deadline=" + deadline +
            ", status=" + status +
            '}';
  }
}
