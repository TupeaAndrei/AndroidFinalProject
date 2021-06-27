package com.example.doctor_patient_app.data.tasks.getTasks;

import android.os.AsyncTask;

import com.example.doctor_patient_app.data.HealthCareDataBase;
import com.example.doctor_patient_app.models.dbEntities.Tablets;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

import java.util.List;

public class GetAllTabletsTask extends AsyncTask<Void,Void, List<Tablets>> {
    private HealthCareDataBase healthCareDataBase;
    private HealthCareRepository.OnGetTabletsListener listener;

    public GetAllTabletsTask(HealthCareDataBase healthCareDataBase,
                             HealthCareRepository.OnGetTabletsListener listener) {
        this.healthCareDataBase = healthCareDataBase;
        this.listener = listener;
    }

    @Override
    protected List<Tablets> doInBackground(Void... voids) {
        return healthCareDataBase.tabletsDAO().getAllTablets();
    }

    @Override
    protected void onPostExecute(List<Tablets> tablets) {
        super.onPostExecute(tablets);
        listener.onSuccess(tablets);
    }
}
