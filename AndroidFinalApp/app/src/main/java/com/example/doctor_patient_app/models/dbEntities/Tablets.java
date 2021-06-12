package com.example.doctor_patient_app.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tablets {
    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name="indications")
    private String indications;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="patient_id")
    private Integer patientId;
}
