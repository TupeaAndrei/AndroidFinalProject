package com.example.doctor_patient_app.models.dbEntities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PatientWithAdvices {
    @Embedded public Patient patient;

    @Relation(
            parentColumn = "id",
            entityColumn = "patient_id"
    )

    public List<DoctorAdvices> advices;
}
