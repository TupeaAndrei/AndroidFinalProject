package com.example.doctor_patient_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.doctor_patient_app.fragments.DoctorInitialFragment;
import com.example.doctor_patient_app.fragments.DoctorLoginFragment;
import com.example.doctor_patient_app.fragments.DoctorRegisterFragment;
import com.example.doctor_patient_app.fragments.PatientInitialFragment;
import com.example.doctor_patient_app.fragments.PatientLoginFragment;
import com.example.doctor_patient_app.fragments.PatientRegisterFragment;
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_id, PatientInitialFragment.class,null,"PatientInitialFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadInitialDoctorFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_id, DoctorInitialFragment.class,null,"DoctorInitialFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadDoctorRegisterFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_id, DoctorRegisterFragment.class,null,"DoctorRegisterFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadDoctorLoginFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_id, DoctorLoginFragment.class,null,"DoctorLoginFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadPatientRegisterFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_id, PatientRegisterFragment.class,null,"PatientRegisterFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadPatientLoginFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_id, PatientLoginFragment.class,null,"PatientLoginFragment")
                .addToBackStack(null).commit();
    }
}