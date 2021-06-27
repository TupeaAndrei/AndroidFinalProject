package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

import java.util.List;

public class GetAllPatientsTask extends AsyncTask<Void,Void, List<Patient>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetPatientListener listener;

    public GetAllPatientsTask(HealthCareDataBase healthCareDataBase, HealthCareRepository.OnGetPatientListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<Patient> doInBackground(Void... voids) {
        return healthCareDataBase.patientDAO().getAllPatients();
    }

    @Override
    protected void onPostExecute(List<Patient> patients) {
        super.onPostExecute(patients);
        listener.onSuccess(patients);
    }
}
