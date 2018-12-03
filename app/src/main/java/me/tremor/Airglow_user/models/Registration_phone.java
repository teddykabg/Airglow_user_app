package me.tremor.Airglow_user.models;


public class Registration_phone {
    private String first_name;
    private String last_name;
    private char sex;
    private String date_of_birth;
    private String phone;
    private String password;

    public Registration_phone(String name, String surname, String date_of_birth, char sex, String password, String phone) {
        this.first_name = name;
        this.last_name = surname;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.password = password;
        this.phone = phone;



    }
}
