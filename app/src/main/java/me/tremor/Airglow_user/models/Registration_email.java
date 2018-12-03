package me.tremor.Airglow_user.models;


public class Registration_email {
    private String first_name;
    private String last_name;
    private char sex;
    private String date_of_birth;
    private String email;
    private String password;

    public Registration_email(String name, String surname, String date_of_birth, char sex, String password, String email) {
        this.first_name = name;
        this.last_name = surname;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.password = password;
        this.email = email;



    }
}
