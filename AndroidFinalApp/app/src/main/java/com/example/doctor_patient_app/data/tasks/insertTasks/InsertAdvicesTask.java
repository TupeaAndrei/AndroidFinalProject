package com.example.doctor_patient_app.data.tasks.insertTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class InsertAdvicesTask extends AsyncTask<DoctorAdvices,Void,Void> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepositoryListener listener;

    public InsertAdvicesTask(HealthCareDataBase healthCareDataBase,
                             HealthCareRepositoryListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(DoctorAdvices... doctorAdvices) {
        healthCareDataBase.doctorAdvicesDAO().insertDoctorAdvice(doctorAdvices[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
