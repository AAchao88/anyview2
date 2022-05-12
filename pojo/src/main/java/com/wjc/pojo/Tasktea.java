package com.wjc.pojo;


public class Tasktea {

  private long id;
  private String taskName;
  private String courseName;
  private long total;
  private String className;
  private java.sql.Timestamp releaseTime;
  private java.sql.Timestamp deadline;
  private long completed;
  private long status;
  private long teacher_id;

  public long getTeacher_id() {
    return teacher_id;
  }

  public void setTeacher_id(long teacher_id) {
    this.teacher_id = teacher_id;
  }

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


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  public java.sql.Timestamp getReleaseTime() {
    return releaseTime;
  }

  public void setReleaseTime(java.sql.Timestamp releaseTime) {
    this.releaseTime = releaseTime;
  }


  public java.sql.Timestamp getDeadline() {
    return deadline;
  }

  public void setDeadline(java.sql.Timestamp deadline) {
    this.deadline = deadline;
  }


  public long getCompleted() {
    return completed;
  }

  public void setCompleted(long completed) {
    this.completed = completed;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


}
