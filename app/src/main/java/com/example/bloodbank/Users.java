package com.example.bloodbank;

public class Users {
    private String name;
    private String age;
    private String gender;
    private String bloodgroup;
    private String phone;
    private String state;
    private String city;
    public Users() {}

    public Users(String name, String age, String gender, String bloodgroup,String phone, String state, String city) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.phone = phone;
        this.state = state;
        this.city = city;
    }

    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public String getBloodGroup(){
        return bloodgroup;
    }
    public String getPhone(){
        return phone;
    }
    public String getState(){
        return state;
    }
    public String getCity(){
        return city;
    }
}
