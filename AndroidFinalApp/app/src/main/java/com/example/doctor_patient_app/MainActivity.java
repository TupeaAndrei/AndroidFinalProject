package com.example.doctor_patient_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.doctor_patient_app.fragments.WelcomeFragment;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;

public class MainActivity extends AppCompatActivity implements IActivityFragmentCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadInitialFragment();
    }

    @Override
    public void loadInitialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame_layout_id,new WelcomeFragment());
        transaction.commit();
    }

    @Override
    public void loadInitialPatientFragment() {

    }

    @Override
    public void loadInitialDoctorFragment() {

    }

    @Override
    public void loadDoctorRegisterFragment() {

    }

    @Override
    public void loadDoctorLoginFragment() {

    }

    @Override
    public void loadPatientRegisterFragment() {

    }

    @Override
    public void loadPatientLoginFragment() {

    }
}