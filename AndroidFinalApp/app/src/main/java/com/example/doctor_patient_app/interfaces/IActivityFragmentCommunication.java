package com.example.doctor_patient_app.interfaces;

import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.Patient;

public interface IActivityFragmentCommunication {
    void loadInitialFragment();
    void loadInitialPatientFragment();
    void loadInitialDoctorFragment();
    void loadDoctorRegisterFragment();
    void loadDoctorLoginFragment();
    void loadPatientRegisterFragment();
    void loadPatientLoginFragment();
    void loadMainPatientFragment(Patient patient);
    void loadMainDoctorFragment(Doctor doctor);
    void loadTabletsFragment(Integer patientId);
    void loadDoctorAdvicesFragment(Integer patientId);
    void loadUpdateDoctorFragment(Doctor doctor);
    void loadUpdatePatientFragment(Patient patient);
    void loadDoctorHelperFragment(Patient patient);
}
