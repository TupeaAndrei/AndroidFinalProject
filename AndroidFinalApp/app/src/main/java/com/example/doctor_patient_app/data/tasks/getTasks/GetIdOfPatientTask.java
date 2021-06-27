package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.repository.HealthCareRepository;


public class GetIdOfPatientTask extends AsyncTask<String,Void,Integer> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetPatientIdListener patientIdListener;

    public GetIdOfPatientTask(HealthCareDataBase healthCareDataBase,
                              HealthCareRepository.OnGetPatientIdListener patientIdListener) {
        this.healthCareDataBase = healthCareDataBase;
        this.patientIdListener = patientIdListener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        return healthCareDataBase.patientDAO().getIdOfPatientWithEmail(strings[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        patientIdListener.onSuccess(integer);
    }
}
