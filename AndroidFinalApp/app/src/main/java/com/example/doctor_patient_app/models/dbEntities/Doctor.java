package com.example.doctor_patient_app.models.dbEntities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.doctor_patient_app.models.DoctorElement;

@Entity
public class Doctor {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="age")
    @Nullable
    private Integer age;

    @ColumnInfo(name="specialization")
    @Nullable
    private String specialization;

    public Doctor(String name, String email, @Nullable Integer age, @Nullable String specialization) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.specialization = specialization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Nullable
    public Integer getAge() {
        return age;
    }

    public void setAge(@Nullable Integer age) {
        this.age = age;
    }

    @Nullable
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(@Nullable String specialization) {
        this.specialization = specialization;
    }

    public DoctorElement convert(){
        return new DoctorElement(name,email,age,specialization);
    }
}
