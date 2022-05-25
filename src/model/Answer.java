package model;

public class Answer {
    private Long id;
    private String text;
    private Boolean status;

    public Answer(Long id, String text, Boolean status) {
        this.id = id;
        this.text = text;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", status=" + status +
                '}';
    }
}
