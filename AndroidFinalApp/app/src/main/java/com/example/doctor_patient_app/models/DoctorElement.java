package com.example.doctor_patient_app.models;

public class DoctorElement {
    private String name;
    private String email;
    private Integer age;
    private String specialization;

    public DoctorElement(String name, String email, Integer age, String specialization) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}