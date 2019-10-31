package com.example.inthujan.finalproject;

public class CustomerDetail {
    public String cus_email;
    public String cus_phone;
    public String cus_name;
    public String cus_age;


    public CustomerDetail(String cus_email, String cus_phone, String cus_name,String cus_age) {
        this.cus_email = cus_email;
        this.cus_phone = cus_phone;
        this.cus_name=cus_name;
        this.cus_age = cus_age;
    }

    public CustomerDetail(){

    }
}
