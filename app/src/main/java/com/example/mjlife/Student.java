package com.example.mjlife;

public class Student {
    public String name, id, password, phone, major, permission;

    public Student(){}

    public Student(String name, String id, String password, String phone, String major, String permission){
        this.name = name;
        this.id = id;
        this.password = password;
        this.phone = phone;
        this.major = major;
        this.permission = "0";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
