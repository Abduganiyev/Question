package Services;

import model.Answer;

public interface AnswerService {
    boolean add(Answer a);
    boolean edit(Answer a);
    boolean delete(Answer a);
    boolean check(Answer a);
}
