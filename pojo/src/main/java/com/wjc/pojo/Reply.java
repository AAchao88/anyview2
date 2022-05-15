package com.wjc.pojo;

public class Reply {
    private long id;
    private long user_id;
    private long question_id;
    private String reply;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", question_id=" + question_id +
                ", reply='" + reply + '\'' +
                '}';
    }
}
