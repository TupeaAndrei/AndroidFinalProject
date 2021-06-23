package com.example.doctor_patient_app.interfaces;

public interface IActivityFragmentCommunication {
    void loadInitialFragment();
    void loadInitialPatientFragment();
    void loadInitialDoctorFragment();
    void loadDoctorRegisterFragment();
    void loadDoctorLoginFragment();
    void loadPatientRegisterFragment();
    void loadPatientLoginFragment();
}
