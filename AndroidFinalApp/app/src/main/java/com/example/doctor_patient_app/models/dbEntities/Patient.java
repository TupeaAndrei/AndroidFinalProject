package com.example.doctor_patient_app.models.dbEntities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

import com.example.doctor_patient_app.models.PatientElement;

@Entity
public class Patient {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name ="name")
    private String name;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="diagnostic")
    @Nullable
    private String diagnostic;

    @ColumnInfo(name="age")
    @Nullable
    private Integer age;

    @ColumnInfo(name="height")
    @Nullable
    private Integer height;

    @ColumnInfo(name="weight")
    @Nullable
    private Integer weight;

    @ColumnInfo(name="doctor_id")
    private Integer doctorId;

    public Patient(String name, String email, @Nullable String diagnostic, @Nullable Integer age, @Nullable Integer height, @Nullable Integer weight, Integer doctorId) {
        this.name = name;
        this.email = email;
        this.diagnostic = diagnostic;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.doctorId = doctorId;
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
    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(@Nullable String diagnostic) {
        this.diagnostic = diagnostic;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }

    public void setAge(@Nullable Integer age) {
        this.age = age;
    }

    @Nullable
    public Integer getHeight() {
        return height;
    }

    public void setHeight(@Nullable Integer height) {
        this.height = height;
    }

    @Nullable
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(@Nullable Integer weight) {
        this.weight = weight;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public PatientElement convert(){
        return new PatientElement(name,email,diagnostic,height,age,weight,doctorId);
    }
}
