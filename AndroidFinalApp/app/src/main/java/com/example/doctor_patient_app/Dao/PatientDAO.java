package com.example.doctor_patient_app.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.models.dbEntities.PatientWithAdvices;
import com.example.doctor_patient_app.models.dbEntities.PatientWithTablets;

import java.util.List;

@Dao
public interface PatientDAO {

    @Query("SELECT * FROM patient")
    List<Patient> getAllPatients();

    @Insert
    void insertPatient(Patient patient);

    @Update
    void updatePatient(Patient patient);

    @Delete
    void deletePatient(Patient patient);

    @Transaction
    @Query("Select * From Patient")
    List<PatientWithTablets> getPatientWithTablets();

    @Transaction
    @Query("Select * From Patient")
    List<PatientWithAdvices> getPatientWithAdvices();
}
