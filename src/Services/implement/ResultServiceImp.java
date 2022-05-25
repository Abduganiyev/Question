package Services.implement;

import Services.ResultService;
import model.Result;
import realization.QuestionDemo;

import java.util.Scanner;

public class ResultServiceImp implements ResultService {
    @Override
    public boolean add(Result r) {
        long nextId = QuestionDemo.results.size() + 1L;
        if (r != null) {
            r.setId(nextId);
            QuestionDemo.results.add(r);
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Result r) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Subject: " + r.getSubject().getName() + "   " +
                           "Fullname: " + r.getUser().getFullname() + "   " +
                           "Score: " + r.getScore() + "   " +
                           "Date: " + r.getSimpleDateFormat().format(r.getDate()));
        System.out.print("New Score: ");
        int score = scanner.nextInt();

        for (Result result : QuestionDemo.results) {
            if (result != null)
                if (result.getId().equals(r.getId())
                        && result.getUser().getId().equals(r.getUser().getId())
                        && result.getSubject().getName().equals(r.getSubject().getName())) {
                    result.setScore(score);
                }
        }

        return true;
    }

    @Override
    public boolean delete(Result r) {
        return false;
    }
}
