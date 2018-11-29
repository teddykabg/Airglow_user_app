package me.tremor.Airglow_user.models;

import java.util.Date;

public class Registration {
    private String name;
    private String surname;
    private String sex;
    private Date date_of_birth;
    private String email_cellphone;
    private String password;

    public Registration(String name, String surname, Date date_of_birth, String sex, String email_cellphone, String password) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.email_cellphone = email_cellphone;
        this.password = password;
    }
}
