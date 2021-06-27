package com.example.doctor_patient_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.doctor_patient_app.fragments.DoctorInitialFragment;
import com.example.doctor_patient_app.fragments.DoctorLoginFragment;
import com.example.doctor_patient_app.fragments.DoctorRegisterFragment;
import com.example.doctor_patient_app.fragments.PatientFragment;
import com.example.doctor_patient_app.fragments.PatientInitialFragment;
import com.example.doctor_patient_app.fragments.PatientLoginFragment;
import com.example.doctor_patient_app.fragments.PatientRegisterFragment;
import com.example.doctor_patient_app.fragments.WelcomeFragment;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.interfaces.IAdapterDatabaseCommunication;
import com.example.doctor_patient_app.interfaces.IFragmentActivityCommunication;
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
        Bundle args = setPatientBundle(patient.getName(),patient.getEmail(),patient.getAge().toString(),
                patient.getHeight().toString(),patient.getWeight().toString(),patient.getDiagnostic());
        patientFragment.setArguments(args);
        transaction.replace(R.id.frame_layout_id,patientFragment,"PatientFragment").addToBackStack(null).commit();
        setListener(patientFragment);
    }

    private Bundle setPatientBundle(String name,String email,String age,
                                    String height,String weight,String diagnostic)
    {
        Bundle args = new Bundle();
        if (age.equals("0")){
            age = notSpecifiedError;
        }
        if (height.equals("0")){
            height = notSpecifiedError;
        }
        if (weight.equals("0")){
            weight = notSpecifiedError;
        }
        if (diagnostic == null){
            diagnostic = notSpecifiedError;
        }
        args.putString("patient_name",name);
        args.putString("patient_email",email);
        args.putString("patient_age",age);
        args.putString("patient_height",height);
        args.putString("patient_weight",weight);
        args.putString("patient_diagnostic",diagnostic);
        return args;
    }

    @Override
    public void updateThisPatient(Integer id) {
        iFragmentActivityCommunication.updatePatient(id);
    }
}