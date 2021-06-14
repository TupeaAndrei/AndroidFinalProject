package com.example.doctor_patient_app.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doctor_patient_app.models.dbEntities.Tablets;

import java.util.List;

@Dao
public interface TabletsDAO {

    @Query("SELECT * FROM tablets")
    List<Tablets> getAllTablets();

    @Insert
    void insertTablet(Tablets tablet);

    @Update
    void updateTablet(Tablets tablet);

    @Delete
    void deleteTablet(Tablets tablet);
}
