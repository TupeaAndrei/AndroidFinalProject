package com.example.doctor_patient_app.data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.doctor_patient_app.Dao.DoctorAdvicesDAO;
import com.example.doctor_patient_app.Dao.DoctorDAO;
import com.example.doctor_patient_app.Dao.PatientDAO;
import com.example.doctor_patient_app.Dao.TabletsDAO;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;
import com.example.doctor_patient_app.models.dbEntities.DoctorsWithPatients;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.models.dbEntities.PatientWithAdvices;
import com.example.doctor_patient_app.models.dbEntities.Tablets;

@Database(entities =
        {Doctor.class, Patient.class, Tablets.class, DoctorAdvices.class},
version = 1)
public abstract class HealthCareDataBase extends RoomDatabase {

    public abstract DoctorDAO doctorDAO();
    public abstract PatientDAO patientDAO();
    public abstract TabletsDAO tabletsDAO();
    public abstract DoctorAdvicesDAO doctorAdvicesDAO();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
