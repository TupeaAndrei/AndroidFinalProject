package com.example.doctor_patient_app.models.dbEntities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

@Entity
public class Patient {
    @PrimaryKey
    private int id;

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
}
