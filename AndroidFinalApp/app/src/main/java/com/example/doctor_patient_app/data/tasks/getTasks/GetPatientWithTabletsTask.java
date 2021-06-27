package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.PatientWithTablets;
import com.example.doctor_patient_app.repository.HealthCareRepository;

import java.util.List;

public class GetPatientWithTabletsTask extends AsyncTask<Integer,Void, List<PatientWithTablets>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetPatientWithTabletsListener listener;

    public GetPatientWithTabletsTask(HealthCareDataBase healthCareDataBase,
                                     HealthCareRepository.OnGetPatientWithTabletsListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<PatientWithTablets> doInBackground(Integer... integers) {
        return healthCareDataBase.patientDAO().getPatientWithTablets(integers[0]);
    }

    @Override
    protected void onPostExecute(List<PatientWithTablets> patientWithTablets) {
        super.onPostExecute(patientWithTablets);
        listener.onSuccess(patientWithTablets);
    }
}
