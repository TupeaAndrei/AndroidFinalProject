package com.example.doctor_patient_app.models.dbEntities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Doctor {
    @PrimaryKey
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
}
