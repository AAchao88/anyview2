package com.wjc.pojo;


public class Course {

  private long id;
  private String courseName;
  private String className;
  private long teacher_id;
  private long total;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  public long getTeacher_id() {
    return teacher_id;
  }

  public void setTeacher_id(long teacher_id) {
    this.teacher_id = teacher_id;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  @Override
  public String toString() {
    return "Course{" +
            "id=" + id +
            ", courseName='" + courseName + '\'' +
            ", className='" + className + '\'' +
            ", teacher_id=" + teacher_id +
            ", total=" + total +
            '}';
  }
}
