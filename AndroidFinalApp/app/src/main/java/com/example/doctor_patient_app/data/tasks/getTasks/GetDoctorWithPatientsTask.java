package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.DoctorsWithPatients;
import com.example.doctor_patient_app.repository.HealthCareRepository;

import java.util.List;

public class GetDoctorWithPatientsTask extends AsyncTask<Integer,Void, List<DoctorsWithPatients>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetDoctorWithPatientsListener listener;

    public GetDoctorWithPatientsTask(HealthCareDataBase healthCareDataBase,
                                     HealthCareRepository.OnGetDoctorWithPatientsListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<DoctorsWithPatients> doInBackground(Integer... integers) {
        return healthCareDataBase.doctorDAO().getDoctorWithPatients(integers[0]);
    }

    @Override
    protected void onPostExecute(List<DoctorsWithPatients> doctorsWithPatients) {
        super.onPostExecute(doctorsWithPatients);
        listener.onSuccess(doctorsWithPatients);
    }
}
