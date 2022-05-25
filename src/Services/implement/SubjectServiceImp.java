package Services.implement;

import Services.SubjectService;
import model.Question;
import model.Subject;
import realization.QuestionDemo;

public class SubjectServiceImp implements SubjectService {
    @Override
    public boolean add(Subject s) {
        long nextId = QuestionDemo.subjects.size() + 1L;
        s.setId(nextId);
        return QuestionDemo.subjects.add(s);
    }

    @Override
    public boolean edit(Subject s) {
        return false;
    }

    @Override
    public boolean delete(Subject s) {
        return false;
    }
}
