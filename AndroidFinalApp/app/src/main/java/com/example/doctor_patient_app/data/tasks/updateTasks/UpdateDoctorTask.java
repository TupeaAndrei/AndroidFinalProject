package com.example.doctor_patient_app.data.tasks.updateTasks;

import android.os.AsyncTask;

import androidx.loader.content.AsyncTaskLoader;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class UpdateDoctorTask extends AsyncTask<Doctor,Void,Void> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepositoryListener listener;

    public UpdateDoctorTask(HealthCareDataBase healthCareDataBase,
                            HealthCareRepositoryListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Doctor... doctors) {
        healthCareDataBase.doctorDAO().updateDoctor(doctors[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
