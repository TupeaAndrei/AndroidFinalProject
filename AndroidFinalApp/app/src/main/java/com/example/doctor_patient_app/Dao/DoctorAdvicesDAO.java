package com.example.doctor_patient_app.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;

import java.util.List;

@Dao
public interface DoctorAdvicesDAO {
    @Query("SELECT * FROM doctor_Advices")
    List<DoctorAdvices> getAllDoctorAdvices();

    @Insert
    void insertDoctorAdvice(DoctorAdvices advice);

    @Update
    void updateDoctorAdvice(DoctorAdvices advice);

    @Delete
    void deleteDoctorAdvice(DoctorAdvices advice);
}
