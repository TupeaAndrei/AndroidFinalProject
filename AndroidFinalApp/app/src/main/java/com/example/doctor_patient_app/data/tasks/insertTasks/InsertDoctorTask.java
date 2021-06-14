package com.example.doctor_patient_app.data.tasks.insertTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class InsertDoctorTask extends AsyncTask<Doctor,Void,Void> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepositoryListener listener;

    public InsertDoctorTask(HealthCareDataBase healthCareDataBase, HealthCareRepositoryListener listener){
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Doctor... doctors) {
        healthCareDataBase.doctorDAO().insertDoctor(doctors[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }

}
