package com.Abhishek.Models;

import org.hibernate.annotations.Proxy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Proxy(lazy = false)
public class Profile {
    @Id
    @GeneratedValue
    private int id;
    private String fname;
    private String lname;
    private String email;
    private long phone;
    private String gender;
    private int age;
    private double height;
    private double weight;
    private String medicalhistroy;
    private String docurl;
    

    public Profile() {
    }


    public Profile(int id, String fname, String lname, String email, long phone, String gender, int age, Double height, Double weight, String medicalhistroy, String docurl) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.medicalhistroy = medicalhistroy;
        this.docurl = docurl;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return this.phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getMedicalhistroy() {
        return this.medicalhistroy;
    }

    public void setMedicalhistroy(String medicalhistroy) {
        this.medicalhistroy = medicalhistroy;
    }

    public String getDocurl() {
        return this.docurl;
    }

    public void setDocurl(String docurl) {
        this.docurl = docurl;
    }




}
