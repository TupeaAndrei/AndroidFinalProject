package com.example.doctor_patient_app.data.tasks.insertTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class InsertPatientTask extends AsyncTask<Patient,Void,Void> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepositoryListener listener;

    public InsertPatientTask(HealthCareDataBase healthCareDataBase,
                             HealthCareRepositoryListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Patient... patients) {
        healthCareDataBase.patientDAO().insertPatient(patients[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
