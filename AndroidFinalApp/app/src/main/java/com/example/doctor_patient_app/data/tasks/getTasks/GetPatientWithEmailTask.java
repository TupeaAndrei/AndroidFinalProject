package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepository;

import java.util.List;

public class GetPatientWithEmailTask extends AsyncTask<String,Void, List<Patient>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetPatientListener listener;

    public GetPatientWithEmailTask(HealthCareDataBase healthCareDataBase,
                                   HealthCareRepository.OnGetPatientListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<Patient> doInBackground(String... strings) {
        return healthCareDataBase.patientDAO().getPatientWithEmail(strings[0]);
    }

    @Override
    protected void onPostExecute(List<Patient> patients) {
        super.onPostExecute(patients);
        listener.onSuccess(patients);
    }
}
