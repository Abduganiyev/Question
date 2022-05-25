package model;

import enam.Role;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class User implements Comparable<User> {
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private Role role;
    private boolean singedIn;
    private Date date;
    private String DataCreateAccount;

    public User(Long id, String fullname, String email, String password, Role role) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.singedIn = false;
        this.date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
        DataCreateAccount = simpleDateFormat.format(date);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isSingedIn() {
        return singedIn;
    }

    public void setSingedIn(boolean singedIn) {
        this.singedIn = singedIn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDataCreateAccount() {
        return DataCreateAccount;
    }

    public void setDataCreateAccount(String dataCreateAccount) {
        DataCreateAccount = dataCreateAccount;
    }

    @Override
    public int compareTo(User o) {
        return this.email.compareTo(o.getEmail());
    }

    @Override
    public String toString() {
        return  "Fullname: " + fullname + '\n' +
                "Email: " + email + '\n' +
                "Password: " + password + '\n' +
                "Role: " + role + '\n' +
                "DataCreateAccount: " + DataCreateAccount +
                '\n';
    }
}
