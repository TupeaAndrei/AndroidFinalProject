package com.example.doctor_patient_app.models;

public class PatientElement {
    private String name;
    private String email;
    private String diagnostic;
    private Integer height;
    private Integer age;
    private Integer weight;
    private Integer doctorId;

    public PatientElement(String name, String email, String diagnostic, Integer height, Integer age, Integer weight, Integer doctorId) {
        this.name = name;
        this.email = email;
        this.diagnostic = diagnostic;
        this.height = height;
        this.age = age;
        this.weight = weight;
        this.doctorId = doctorId;
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

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }
}
