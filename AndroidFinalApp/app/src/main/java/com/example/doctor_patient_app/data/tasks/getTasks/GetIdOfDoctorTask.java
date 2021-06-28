package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.repository.HealthCareRepository;

public class GetIdOfDoctorTask extends AsyncTask<String,Void,Integer> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetDoctorIdWithEmailListener listener;

    public GetIdOfDoctorTask(HealthCareDataBase healthCareDataBase,
                             HealthCareRepository.OnGetDoctorIdWithEmailListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        return healthCareDataBase.doctorDAO().getIdOfDoctorWithEmail(strings[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        listener.onSuccess(integer);
    }
}
