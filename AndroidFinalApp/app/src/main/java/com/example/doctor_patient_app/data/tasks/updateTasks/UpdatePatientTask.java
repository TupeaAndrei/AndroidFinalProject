package com.example.doctor_patient_app.data.tasks.updateTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

import java.nio.channels.AsynchronousChannelGroup;

public class UpdatePatientTask extends AsyncTask<Patient,Void,Void> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepositoryListener listener;

    public UpdatePatientTask(HealthCareDataBase healthCareDataBase, HealthCareRepositoryListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Patient... patients) {
        healthCareDataBase.patientDAO().updatePatient(patients[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
