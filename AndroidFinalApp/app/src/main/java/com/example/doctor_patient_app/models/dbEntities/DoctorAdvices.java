package com.example.doctor_patient_app.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Doctor_Advices")
public class DoctorAdvices {
    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "advice_text")
    private String adviceText;

    @ColumnInfo(name = "patient_id")
    private Integer patientId;
}
