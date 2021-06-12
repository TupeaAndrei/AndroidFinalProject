package com.example.doctor_patient_app;

import android.app.Application;

import androidx.room.Room;

import com.example.doctor_patient_app.data.HealthCareDataBase;

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static HealthCareDataBase healthCareDataBase;
    private final String dataBaseName= "HealthCareDataBase";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupDataBase();
    }

    private void setupDataBase(){
        healthCareDataBase = Room.databaseBuilder(getApplicationContext(),
                HealthCareDataBase.class,
                dataBaseName)
                .build();
    }

    public static ApplicationController getInstance(){
        return instance;
    }

    public static HealthCareDataBase getHealthCareDataBase(){
        return healthCareDataBase;
    }
}
