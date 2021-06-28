package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.repository.HealthCareRepository;

public class GetDoctorWithEmailTask extends AsyncTask<String,Void, Doctor> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetDoctorWithEmailListener listener;

    public GetDoctorWithEmailTask(HealthCareDataBase healthCareDataBase,
                                  HealthCareRepository.OnGetDoctorWithEmailListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Doctor doInBackground(String... strings) {
        return healthCareDataBase.doctorDAO().getDoctorWithEmail(strings[0]);
    }

    @Override
    protected void onPostExecute(Doctor doctor) {
        super.onPostExecute(doctor);
        listener.onSuccess(doctor);
    }
}
