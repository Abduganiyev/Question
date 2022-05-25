package Services.implement;

import Services.*;
import enam.Role;
import model.*;
import realization.QuestionDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DemonstrationServiceImp implements DemonstrationService {

    static Scanner scanner;
    static User currentUser;
    static List<Subject> subjects;
    static List<Question> questions;
    static List<Answer> answers;
    static ResultService resultService;
    static SubjectService subjectService;
    static AnswerService answerService;
    static QuestionService questionService;

    @Override
    public void studentMenu() {
        scanner = new Scanner(System.in);

        currentUser = QuestionDemo.currentUser;

        int choise = 0;
        System.out.println("==========Student Menu==========");
        System.out.println("1. Test yechish");
        System.out.println("2. View Results");
        System.out.println("3. Orqaga");
        choise = scanner.nextInt();
        switch (choise) {
            case 1:
                quiz();
                break;
            case 2:
                viewResult(currentUser);
                break;
            case 3:
                QuestionDemo.currentUser.setSingedIn(false);
                break;
            default:
                System.out.println("Incorrect option!\n");
                break;
        }

    }

    @Override
    public void masterMenu() {
        scanner = new Scanner(System.in);

        currentUser = QuestionDemo.currentUser;

        int choise = 0;
        System.out.println("==========Master Menu==========");
        System.out.println("1. Add Subject");
        System.out.println("2. Add Question");
        System.out.println("3. Edit Result");
        System.out.println("4. View Students");
        System.out.println("5. Orqaga");
        choise = scanner.nextInt();

        switch (choise) {
            case 1:
                addSubject();
                break;
            case 2:
                addQuestons();
                break;
            case 3:
                editResults();
                break;
            case 4:
                viewStudents();
                break;
            case 5:
                currentUser.setSingedIn(false);
                break;
            default:
                System.out.println("Incorrect option!");
        }
    }

    private void editResults() {
        scanner = new Scanner(System.in);

        resultService = new ResultServiceImp();

        List<User> students = new ArrayList<>();
        for (User user : QuestionDemo.users) {
            if (user != null)
                if (user.getRole() == Role.STUDENT)
                    students.add(user);
        }

        viewStudents();
        System.out.print("Choose the student: ");
        int choice = scanner.nextInt();

        User student = students.get(choice - 1);

        viewResult(student);
        System.out.print("choose: ");
        choice = scanner.nextInt();

        List<Result> resultList = new ArrayList<>();
        for (Result result : QuestionDemo.results) {
            if (result != null)
                if (result.getUser().getFullname().equals(student.getFullname()))
                    resultList.add(result);
        }

        Result result = resultList.get(choice - 1);
        boolean edited = resultService.edit(result);
        if (edited)
            System.out.println("Succesfully edited!");
    }

    private void viewStudents() {
        int index = 1;
        for (User user : QuestionDemo.users) {
            if (user != null)
                if (user.getRole() == Role.STUDENT)
                    System.out.println(index++ + ".\n" +
                                       "Fullname: " + user.getFullname() + "\n" +
                                       "Email: " + user.getEmail() + "\n" +
                                       "Data Create Account: " + user.getDataCreateAccount() + "\n");
        }
    }

    private void addQuestons() {
        scanner = new Scanner(System.in);

        printSubjects();

        int choose = scanner.nextInt();
        Subject subject = QuestionDemo.subjects.get(choose - 1);

        List<Question> questionList = new ArrayList<>(subject.getQuestions());
        long nextId = questionList.size() + 1L;

        System.out.print("How many quetions do you want to add?");
        int i = scanner.nextInt();
        for (int j = 1; j <= i; j++) {
            Question question = creatQuestion();
            question.setId(nextId++);
            questionList.add(question);
        }

        QuestionDemo.subjects.get(choose - 1).setQuestions(questionList);

    }

    private void addSubject() {
        scanner = new Scanner(System.in);

        subjectService = new SubjectServiceImp();

        System.out.println("name: ");
        String name = scanner.next();

        List<Question> questionList = new ArrayList<>();

        System.out.print("How many quetions do you want to add?");
        int i = scanner.nextInt();
        for (int j = 1; j <= i; j++) {
            Question question = creatQuestion();
            question.setId((long) j);
            questionList.add(question);
            System.out.println("This succesfully added");
        }

        subjectService.add(new Subject(name, questionList));
        // TODO: 25.05.2022 logic
    }

    private static Question creatQuestion() {
        scanner = new Scanner(System.in);

        questionService = new QuestionServiceImp();
        answerService = new AnswerServiceImp();

        Question question = new Question();

        System.out.print("Text: ");
        String text = scanner.nextLine();

        List<Answer> answerList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            scanner = new Scanner(System.in);
            System.out.print(i + ". answer: ");
            String ans = scanner.nextLine();

            scanner = new Scanner(System.in);
            System.out.print(i + ". status: ");
            String stringStatus = scanner.next();
            boolean status = stringStatus.toLowerCase(Locale.ROOT).equals("true");

            answerList.add(new Answer((long) i,ans, status));
        }
        question.setText(text);
        question.setAnswers(answerList);

        return question;
    }

    @Override
    public void direktorMenu() {

    }

    private void quiz() {
        scanner = new Scanner(System.in);
        resultService = new ResultServiceImp();
        currentUser = QuestionDemo.currentUser;

        Subject subject = null;
        int score = 0;

        printSubjects();
        System.out.print("Choose the subject: ");
        int choise = scanner.nextInt();
        subject = subjects.get(choise - 1);


        for (Question question : subject.getQuestions()) {
            System.out.println(question.getId() + ". " + question.getText());
            printAnswers(question);
            List<Answer> ans = question.getAnswers();
            System.out.print("\nchoose: ");
            choise = scanner.nextInt();

            if (answers.size() >= choise) {
                Answer answer = answers.get(choise-1);
                Boolean status = answer.getStatus();
                if (status) {
                    // TODO: 24.05.2022 logic here correct answer
                    score+=2;

                    System.out.println("BARAKALLA!\n");
                } else {
                    System.out.println("Notog'ri javob!\n");
                }
            } else {
                System.out.println("Inncorect answer!\n");
            }
        }

        Result result = new Result(currentUser, subject, score);
        resultService.add(result);
        System.out.println(result);
    }
    private static void printSubjects() {
        subjects = QuestionDemo.subjects;
        System.out.println("========== Subjects ==========");
        int index = 1;
        for (Subject subject : subjects) {
            if (subject != null)
                System.out.println(index++ +
                        ". " + subject.getName());
        }
    }

    private void printQuestions(Subject s) {
        System.out.println();
        System.out.println("==== " + s.getName() + " ====");
        questions = s.getQuestions();
        int index = 1;
        if (questions != null) {
            for (Question question : questions) {
                if (question != null)
                    System.out.println(index++ + ". " + question.getText());
            }
        }
    }

    private void printAnswers(Question q) {
        scanner = new Scanner(System.in);
        answers = q.getAnswers();

        if (answers != null) {
            for (Answer answer : answers) {
                if (answer != null)
                    System.out.print(answer.getId() + ")" + answer.getText() + "\t");
            }
        }
    }

    private void viewResult(User currentUser) {
        int index = 1;
        for (Result result : QuestionDemo.results) {
            if (result != null)
                if (result.getUser().getEmail().equals(currentUser.getEmail())) {
                    System.out.println(index++ + ". ");
                    System.out.println(result + "\n");
                }
        }
    }
}
