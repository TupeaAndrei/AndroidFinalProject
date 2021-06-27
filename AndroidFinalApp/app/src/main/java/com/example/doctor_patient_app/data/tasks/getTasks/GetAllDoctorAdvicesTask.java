package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

import java.util.List;

public class GetAllDoctorAdvicesTask extends AsyncTask<Void,Void, List<DoctorAdvices>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetDoctorAdvicesListener listener;

    public GetAllDoctorAdvicesTask(HealthCareDataBase healthCareDataBase,
                                   HealthCareRepository.OnGetDoctorAdvicesListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<DoctorAdvices> doInBackground(Void... voids) {
        return healthCareDataBase.doctorAdvicesDAO().getAllDoctorAdvices();
    }

    @Override
    protected void onPostExecute(List<DoctorAdvices> doctorAdvices) {
        super.onPostExecute(doctorAdvices);
        listener.onSuccess(doctorAdvices);
    }
}
