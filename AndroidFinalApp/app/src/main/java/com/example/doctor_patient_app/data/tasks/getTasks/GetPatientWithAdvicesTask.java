package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.PatientWithAdvices;
import com.example.doctor_patient_app.repository.HealthCareRepository;

import java.util.List;

public class GetPatientWithAdvicesTask extends AsyncTask<Integer,Void, List<PatientWithAdvices>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetPatientWithAdvicesListener listener;

    public GetPatientWithAdvicesTask(HealthCareDataBase healthCareDataBase,
                                     HealthCareRepository.OnGetPatientWithAdvicesListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<PatientWithAdvices> doInBackground(Integer... integers) {
        return healthCareDataBase.patientDAO().getPatientWithAdvices(integers[0]);
    }

    @Override
    protected void onPostExecute(List<PatientWithAdvices> patientWithAdvices) {
        super.onPostExecute(patientWithAdvices);
        listener.onSuccess(patientWithAdvices);
    }
}
