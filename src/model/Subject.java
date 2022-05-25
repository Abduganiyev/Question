package model;

import java.util.List;

public class Subject {
    private Long id;
    private String name;
    private List<Question> questions;

    public Subject(Long id, String name, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.questions = questions;
    }

    public Subject() {

    }

    public Subject(String name, List<Question> questionList) {
        this.name = name;
        this.questions = questionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", questions=" + questions +
                '}';
    }
}
