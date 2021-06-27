package com.example.doctor_patient_app.repository;

import com.example.doctor_patient_app.ApplicationController;
import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllDoctorAdvicesTask;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllDoctorsTask;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllPatientsTask;
import com.example.doctor_patient_app.data.tasks.getTasks.GetAllTabletsTask;
import com.example.doctor_patient_app.data.tasks.getTasks.GetIdOfPatientTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertAdvicesTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertDoctorTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertPatientTask;
import com.example.doctor_patient_app.data.tasks.insertTasks.InsertTabletTask;
import com.example.doctor_patient_app.data.tasks.updateTasks.UpdatePatientTask;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.models.dbEntities.Tablets;

import java.util.ArrayList;
import java.util.List;

public class HealthCareRepository {
    private HealthCareDataBase healthCareDataBase;

    public static interface OnGetDoctorListener{
        void onSuccess(List<Doctor> doctors);
    }

    public static interface OnGetPatientListener{
        void onSuccess(List<Patient> patients);
    }

    public static interface OnGetTabletsListener{
        void onSuccess(List<Tablets> tablets);
    }

    public static interface OnGetDoctorAdvicesListener{
        void onSuccess(List<DoctorAdvices> doctorAdvices);
    }

    public static interface OnGetPatientIdListener{
        void onSuccess(Integer patientId);
    }

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

    public void getAllDoctors(OnGetDoctorListener listener){
        new GetAllDoctorsTask(healthCareDataBase,listener).execute();
    }

    public void getAllPatients(OnGetPatientListener listener){
        new GetAllPatientsTask(healthCareDataBase,listener).execute();
    }

    public void getIdOfPatient(String email,OnGetPatientIdListener listener){
        new GetIdOfPatientTask(healthCareDataBase,listener).execute(email);
    }

    public void getAllTablets(OnGetTabletsListener listener){
        new GetAllTabletsTask(healthCareDataBase,listener).execute();
    }

    public void getAllDoctorAdvices(OnGetDoctorAdvicesListener listener){
        new GetAllDoctorAdvicesTask(healthCareDataBase,listener).execute();
    }

    public void updatePatient(Patient patient,HealthCareRepositoryListener listener){
        new UpdatePatientTask(healthCareDataBase,listener).execute(patient);
    }
}
