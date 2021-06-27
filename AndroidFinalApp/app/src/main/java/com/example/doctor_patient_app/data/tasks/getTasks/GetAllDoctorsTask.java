package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

import java.util.List;

public class GetAllDoctorsTask extends AsyncTask<Void,Void, List<Doctor>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetDoctorListener listener;

    public GetAllDoctorsTask(HealthCareDataBase healthCareDataBase, HealthCareRepository.OnGetDoctorListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<Doctor> doInBackground(Void... voids) {
        return healthCareDataBase.doctorDAO().getAllDoctors();
    }

    @Override
    protected void onPostExecute(List<Doctor> doctors) {
        super.onPostExecute(doctors);
        listener.onSuccess(doctors);
    }
}
