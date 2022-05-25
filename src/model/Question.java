package model;

import java.util.List;

public class Question {
    private Long id;
    private String text;
    private List<Answer> answers;
    private String userAnswer;

    public Question(Long id, String text, List<Answer> answers, String userAnswer) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.userAnswer = userAnswer;
    }

    public Question() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                ", userAnswer='" + userAnswer + '\'' +
                '}';
    }
}
