package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {
    private Long id;
    private User user;
    private Subject subject;
    private Integer score;
    private Date date;
    private SimpleDateFormat simpleDateFormat;

    public Result(Long id, User user, Subject subject, Integer score) {
        this.id = id;
        this.user = user;
        this.subject = subject;
        this.score = score;
        this.date = new Date();
        this.simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
    }

    public Result(User user, Subject subject, Integer score) {
        this.user = user;
        this.subject = subject;
        this.score = score;
        this.date = new Date();
        this.simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    @Override
    public String toString() {
        return  "Fullname: " + user.getFullname() + "\n" +
                "Subject: " + subject.getName() + "\n" +
                "Score:" + score + "\n" +
                "Date: " + simpleDateFormat.format(date);
    }
}
