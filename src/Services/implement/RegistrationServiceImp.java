package Services.implement;

import Services.RegistrationService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enam.Role;
import model.User;
import realization.*;

public class RegistrationServiceImp implements RegistrationService {
    static Scanner scanner;
    static User currentUser;
    @Override
    public boolean signIn() {
        scanner = new Scanner(System.in);
        System.out.println("email: ");
        String email = scanner.next();

        System.out.println("password: ");
        String password = scanner.next();

        for (User user : QuestionDemo.users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                QuestionDemo.currentUser = user;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signUp() {
        scanner = new Scanner(System.in);
        int userId = QuestionDemo.users.size() + 1;
        System.out.println("Fullname: ");
        String name = scanner.next();

        String email = "";
        System.out.println("Email: ");
        email = scanner.next();
        while (!validated(email)){
            System.out.println("Email: ");
            email = scanner.next();
        }

        System.out.println("Password: ");
        String password = scanner.next();

        int counter = 3;
        while (counter-- != 0) {
            System.out.println("confirm: ");
            String confirm = scanner.next();
            if (password.equals(confirm))
                break;
        }

        for (Role value : Role.values()) {
            System.out.println(value.ordinal() + 1 + ". " + value.name());
        }
        System.out.println("Choose the role: ");
        int roleindex = scanner.nextInt();
        Role role = Role.STUDENT;
        counter = 3;
        while (counter-- != 0) {
            if (Role.values().length > roleindex-1) {
                role = Role.values()[roleindex - 1];
                break;
            }
            for (Role value : Role.values()) {
                System.out.println(value.ordinal() + 1 + ". " + value.name());
            }
            System.out.println("Choose the role: ");
            roleindex = scanner.nextInt();
        }

        currentUser = new User((long)userId,name,email,password,role);
        return QuestionDemo.users.add(currentUser);
    }

    public static Boolean validated(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean matches = matcher.matches();
        return matches;
    }
}
