package realization;

import Services.DemonstrationService;
import Services.RegistrationService;
import Services.implement.DemonstrationServiceImp;
import Services.implement.RegistrationServiceImp;
import enam.Role;
import model.*;

import java.util.*;

public class QuestionDemo {
    public static Set<User> users;
    public static List<Subject> subjects;
    public static User currentUser;
    public static List<Result> results;
    public static RegistrationService registrationService;
    public static DemonstrationService demonstrationService;

    public static void main(String[] args) {
         users = new TreeSet<>();
         subjects = new ArrayList<>();
         results = new ArrayList<>();

        users.add(new User(1L, "UmarAli", "umar@mail.ru", "asd123", Role.STUDENT));
        users.add(new User(2L,"Bobur","bobur@mail.ru","asd123", Role.MASTER));

        Subject subject1 = new Subject();

        List<Question> questionsList1 = new ArrayList<>();

        Question question1 = new Question();
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer(1L,"Molekula",true));
        answers1.add(new Answer(2L,"Atom",false));
        answers1.add( new Answer(3L,"Proton",false));
        answers1.add(new Answer(4L,"Yer",false));
        question1.setId(1L);
        question1.setText("Eng kichik narsa nma?");
        question1.setAnswers(answers1);

        Question question2 = new Question();
        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer(1L,"7800 g/sm3",true));
        answers2.add(new Answer(2L,"7800 kg/m3",false));
        answers2.add( new Answer(3L,"0,78 kg/m3",false));
        answers2.add(new Answer(4L,"78 g/sm3",false));
        question2.setId(2L);
        question2.setText("7,8 kg li temirning hajmi 1000 sm3, uning zichligi qancha?");
        question2.setAnswers(answers2);

        Question question3 = new Question();
        List<Answer> answers3 = new ArrayList<>();
        answers3.add(new Answer(1L,"m,s, N",true));
        answers3.add(new Answer(2L,"km,soat, m/s",false));
        answers3.add( new Answer(3L,"m/s, sek, kg/m3",false));
        answers3.add(new Answer(4L,"m/s, kg/m3, N",false));
        question3.setId(3L);
        question3.setText("Tezlik, zichlik, kuch kattaliklarning o`lchov birliklarini toping?");
        question3.setAnswers(answers3);

        questionsList1.add(question1);
        questionsList1.add(question2);
        questionsList1.add(question3);

        subject1.setId(1L);
        subject1.setName("Fizkia");
        subject1.setQuestions(questionsList1);

        Subject subject2 = new Subject();

        List<Question> questionsList2 = new ArrayList<>();

        Question question4 = new Question();
        List<Answer> answers4 = new ArrayList<>();
        answers4.add(new Answer(1L,"10",false));
        answers4.add(new Answer(2L,"12",true));
        answers4.add(new Answer(3L,"13",false));
        answers4.add(new Answer(4L,"11",false));
        question4.setId(1L);
        question4.setText("Noma’lum 4.8g modda suyuqlanmasi elektroliz qilinganda anodda\n" +
                "6.72 l(n.sh) vodorod ajraldi. Noma’lum modda tarkibidagi\n" +
                "elektron, proton va neytronlar yig’indisini aniqlang. ");
        question4.setAnswers(answers4);

        Question question5 = new Question();
        List<Answer> answers5 = new ArrayList<>();
        answers5.add(new Answer(1L,"195",false));
        answers5.add(new Answer(2L,"196",false));
        answers5.add(new Answer(3L,"198",false));
        answers5.add(new Answer(4L,"197",true));
        question5.setId(2L);
        question5.setText("Noma’lum 19.35g modda suyuqlanmasi elektroliz qilinganda\n" +
                "3.36 l(n.sh) anodda vodorod ajraldi. Noma’lum modda tarkibidagi\n" +
                "elektron, proton va neytronlar yig’indisini aniqlang. ");
        question5.setAnswers(answers5);

        Question question6 = new Question();
        List<Answer> answers6 = new ArrayList<>();
        answers6.add(new Answer(1L,"Mg",false));
        answers6.add(new Answer(2L,"Be",false));
        answers6.add(new Answer(3L,"AI",false));
        answers6.add(new Answer(4L,"B",true));
        question6.setId(3L);
        question6.setText("Noma’lum elementning tabiatda bir-biridan 2 m.a.b farq qiluvchi\n" +
                "2 ta izatopi uchraydi. Agar bitta izatop atomi tarkibida neytronning\n" +
                "mol 33.33% ulushi boshqasida 36.59% esa ga teng bo’lsa,\n" +
                "elementni aniqlang.");
        question6.setAnswers(answers6);
        questionsList2.add(question4);
        questionsList2.add(question5);
        questionsList2.add(question6);
        subject2.setId(2L);
        subject2.setName("Ximya");
        subject2.setQuestions(questionsList2);

        subjects.add(subject1);
        subjects.add(subject2);


        Scanner scanner = new Scanner(System.in);
        registrationService = new RegistrationServiceImp();
        int choice = -1;
        do {
            scanner = new Scanner(System.in);
            showMainMenu();
            try {
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
            } catch (Exception e) {
                scanner = new Scanner(System.in);
            }

            switch (choice) {
                case 1:
                    singIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Incorrect option!\n");
            }
        } while (choice != 3);
    }

    private static void showMainMenu() {
        System.out.println("Menu");
        System.out.println("1. Sign In");
        System.out.println("2. Sign Up");
        System.out.println("3. Exit");
    }

    private static void signUp() {
        boolean isSucces = registrationService.signUp();
        if (isSucces) {
            System.out.println("Succes!\n");
        } else {
            System.out.println("this mail account already exists\n");
        }
    }

    private static void singIn() {
        boolean isSucces = registrationService.signIn();
        if (isSucces) {
            currentUser.setSingedIn(isSucces);
            demonstrationService = new DemonstrationServiceImp();

            while (currentUser.isSingedIn()) {
                switch (currentUser.getRole()) {
                    case STUDENT:
                    demonstrationService.studentMenu();
                        break;
                    case MASTER:
                    demonstrationService.masterMenu();
                        break;
                    case DIRECTOR:
                    demonstrationService.direktorMenu();
                        break;
                }
            }
        } else
            System.out.println("User not found!\n");
    }
}