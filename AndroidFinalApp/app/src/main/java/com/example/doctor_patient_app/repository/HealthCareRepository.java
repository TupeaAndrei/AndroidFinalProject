package com.example.doctor_patient_app.repository;

import com.example.doctor_patient_app.ApplicationController;
import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllDoctorAdvicesTask;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllDoctorsTask;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllPatientsTask;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllTabletsTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertAdvicesTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertDoctorTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertPatientTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertTabletTask;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.models.dbEntities.Tablets;

import java.util.ArrayList;
import java.util.List;

public class HealthCareRepository {
    private HealthCareDataBase healthCareDataBase;

    public HealthCareRepository(){
        healthCareDataBase = ApplicationController.getHealthCareDataBase();
    }

    public void insertDoctor(Doctor doctor,HealthCareRepositoryListener listener){
        new InsertDoctorTask(healthCareDataBase,listener).execute(doctor);
    }

    public void insertPatient(Patient patient,HealthCareRepositoryListener listener){
        new InsertPatientTask(healthCareDataBase,listener).execute(patient);
    }

    public void insertTablet(Tablets tablet,HealthCareRepositoryListener listener){
        new InsertTabletTask(healthCareDataBase,listener).execute(tablet);
    }

    public void insertDoctorAdvice(DoctorAdvices advice,HealthCareRepositoryListener listener){
        new InsertAdvicesTask(healthCareDataBase,listener).execute(advice);
    }

    public void getAllDoctors(HealthCareRepositoryListener listener){
        new GetAllDoctorsTask(healthCareDataBase,listener).execute();
    }

    public void getAllPatients(HealthCareRepositoryListener listener){
        new GetAllPatientsTask(healthCareDataBase,listener).execute();
    }

    public void getAllTablets(HealthCareRepositoryListener listener){
        new GetAllTabletsTask(healthCareDataBase,listener).execute();
    }

    public void getAllDoctorAdvices(HealthCareRepositoryListener listener){
        new GetAllDoctorAdvicesTask(healthCareDataBase,listener).execute();
    }
}
