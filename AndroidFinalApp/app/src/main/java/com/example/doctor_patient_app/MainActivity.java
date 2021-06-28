package com.example.doctor_patient_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.doctor_patient_app.fragments.DoctorAdvicesFragment;
import com.example.doctor_patient_app.fragments.DoctorFragment;
import com.example.doctor_patient_app.fragments.DoctorInitialFragment;
import com.example.doctor_patient_app.fragments.DoctorLoginFragment;
import com.example.doctor_patient_app.fragments.DoctorRegisterFragment;
import com.example.doctor_patient_app.fragments.PatientFragment;
import com.example.doctor_patient_app.fragments.PatientInitialFragment;
import com.example.doctor_patient_app.fragments.PatientLoginFragment;
import com.example.doctor_patient_app.fragments.PatientRegisterFragment;
import com.example.doctor_patient_app.fragments.TabletsFragment;
import com.example.doctor_patient_app.fragments.UpdateDoctorFragment;
import com.example.doctor_patient_app.fragments.UpdatePatientFragment;
import com.example.doctor_patient_app.fragments.WelcomeFragment;
import com.example.doctor_patient_app.helpers.BundleMaker;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.interfaces.IAdapterDatabaseCommunication;
import com.example.doctor_patient_app.interfaces.IFragmentActivityCommunication;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.Patient;

public class MainActivity extends AppCompatActivity implements IActivityFragmentCommunication, IAdapterDatabaseCommunication {

    private final String notSpecifiedError = "Not specified!";

    private IFragmentActivityCommunication iFragmentActivityCommunication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadInitialFragment();
    }

    public void setListener(IFragmentActivityCommunication iFragmentActivityCommunication){
        this.iFragmentActivityCommunication = iFragmentActivityCommunication;
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

    @Override
    public void loadMainPatientFragment(Patient patient) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        PatientFragment patientFragment = new PatientFragment();
        Bundle args = BundleMaker.setPatientBundle(patient.getName(),patient.getEmail(),patient.getAge().toString(),
                patient.getHeight().toString(),patient.getWeight().toString(),patient.getDiagnostic());
        patientFragment.setArguments(args);
        transaction.replace(R.id.frame_layout_id,patientFragment,"PatientFragment").addToBackStack(null).commit();
        setListener(patientFragment);
    }

    @Override
    public void loadMainDoctorFragment(Doctor doctor) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        DoctorFragment doctorFragment = new DoctorFragment();
        Bundle args = BundleMaker.setDoctorBundle(doctor.getName(),doctor.getEmail(),doctor.getAge().toString(),
                doctor.getSpecialization());
        doctorFragment.setArguments(args);
        transaction.replace(R.id.frame_layout_id,doctorFragment,"DoctorFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadTabletsFragment(Integer patientId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        TabletsFragment tabletsFragment = new TabletsFragment();
        Bundle args = new Bundle();
        args.putInt("patient_id",patientId);
        tabletsFragment.setArguments(args);
        transaction.replace(R.id.frame_layout_id,tabletsFragment,"TabletsFragment").
                addToBackStack(null).commit();
    }

    @Override
    public void loadDoctorAdvicesFragment(Integer patientId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        DoctorAdvicesFragment doctorAdvicesFragment = new DoctorAdvicesFragment();
        Bundle args = new Bundle();
        args.putInt("patient_id",patientId);
        doctorAdvicesFragment.setArguments(args);
        transaction.replace(R.id.frame_layout_id,doctorAdvicesFragment,"DoctorAdvicesFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadUpdateDoctorFragment(Doctor doctor) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        UpdateDoctorFragment updateDoctorFragment = new UpdateDoctorFragment();
        Bundle args = new Bundle();
        args.putInt("doctor_id",doctor.getId());
        args.putString("doctor_name",doctor.getName());
        args.putString("doctor_email",doctor.getEmail());
        updateDoctorFragment.setArguments(args);
        transaction.replace(R.id.frame_layout_id,updateDoctorFragment,"UpdateDoctorFragment")
                .addToBackStack(null).commit();
    }

    @Override
    public void loadUpdatePatientFragment(Patient patient) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        UpdatePatientFragment updatePatientFragment = new UpdatePatientFragment();
        Bundle args = BundleMaker.setBundleForUpdatePatient(patient.getId(),patient.getName(),
                patient.getEmail(),patient.getDoctorId());
        updatePatientFragment.setArguments(args);
        transaction.replace(R.id.frame_layout_id,updatePatientFragment,"UpdatePatientFragment")
                .addToBackStack(null).commit();
    }


    @Override
    public void updateThisPatient(Integer id) {
        iFragmentActivityCommunication.updatePatient(id);
    }
}