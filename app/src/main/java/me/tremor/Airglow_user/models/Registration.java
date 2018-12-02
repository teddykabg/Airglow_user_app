package me.tremor.Airglow_user.models;

import java.util.Date;

public class Registration {
    private String first_name;
    private String last_name;
    private String sex;
    private Date date_of_birth;
    private String email;
    private String phone_number;
    private String password;

    public Registration(String name, String surname, Date date_of_birth, String sex,String password,String email_cellphone) {
        this.first_name = name;
        this.last_name = surname;
        this.sex = sex;
        this.date_of_birth = date_of_birth;
        this.password = password;
        if(email_cellphone.contains("@")) {
            this.email = email_cellphone;
            this.phone_number=null;
        }
        else {
            this.phone_number = email_cellphone;
            this.email=null;
        }
    }
}
