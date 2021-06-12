package com.example.doctor_patient_app.models.dbEntities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class DoctorsWithPatients {
    @Embedded private Doctor doctor;
    @Relation(
            parentColumn = "id",
            entityColumn = "doctor_id"
    )
    private List<Patient> patients;
}
