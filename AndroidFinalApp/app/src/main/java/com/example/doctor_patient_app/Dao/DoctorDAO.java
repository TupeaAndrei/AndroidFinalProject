package com.example.doctor_patient_app.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.DoctorsWithPatients;

import java.util.List;

@Dao
public interface DoctorDAO {

    @Query("Select * From doctor")
    List<Doctor> getAllDoctors();

    @Query("Select id FROM Doctor where email == :doctorEmail")
    Integer getDoctorIdWithEmail(String doctorEmail);

    @Query("Select * FROM Doctor where email== :doctorEmail")
    Doctor getDoctorWithEmail(String doctorEmail);

    @Insert
    void insertDoctor(Doctor doctor);

    @Update
    void updateDoctor(Doctor doctor);

    @Delete
    void deleteDoctor(Doctor doctor);

    @Transaction
    @Query("Select * From Doctor where id == :doctorId")
    List<DoctorsWithPatients> getDoctorWithPatients(Integer doctorId);

    @Query("Select id From Doctor where email == :doctorEmail")
    Integer getIdOfDoctorWithEmail(String doctorEmail);
}
