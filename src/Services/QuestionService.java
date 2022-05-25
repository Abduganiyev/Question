package Services;

import model.Question;

public interface QuestionService {
    boolean add(Question q);
    boolean edit(Question q);
    boolean delete(Question q);


}
