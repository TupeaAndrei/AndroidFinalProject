package com.example.doctor_patient_app.data.tasks.insertTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Tablets;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class InsertTabletTask extends AsyncTask<Tablets,Void,Void> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepositoryListener listener;

    public InsertTabletTask(HealthCareDataBase healthCareDataBase,
                            HealthCareRepositoryListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Tablets... tablets) {
        healthCareDataBase.tabletsDAO().insertTablet(tablets[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.onSuccess();
    }
}
