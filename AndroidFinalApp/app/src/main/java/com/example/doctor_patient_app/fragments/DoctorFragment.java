package com.example.doctor_patient_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.adapters.PatientAdapter;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.DoctorsWithPatients;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepository;

import java.util.ArrayList;
import java.util.List;

public class DoctorFragment extends Fragment {
    private TextView doctorName;
    private TextView doctorEmail;
    private TextView doctorAge;
    private TextView doctorSpecialization;

    private Button updateButton;

    private Integer doctor_Id = 0;
    private String doctorNameString;
    private String doctorEmailString;
    private String doctorAgeString;
    private String doctorSpecializationString;

    private IActivityFragmentCommunication iActivityFragmentCommunication;

    private ArrayList<Patient> patientList = new ArrayList<Patient>();
    private PatientAdapter patientAdapter = new PatientAdapter(patientList);

    private HealthCareRepository healthCareRepository = new HealthCareRepository();

    public static DoctorFragment newInstance(){
        Bundle args = new Bundle();

        DoctorFragment doctorFragment = new DoctorFragment();
        doctorFragment.setArguments(args);
        return doctorFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            doctorNameString = getArguments().getString("doctor_name");
            doctorEmailString = getArguments().getString("doctor_email");
            doctorAgeString = getArguments().getString("doctor_age");
            doctorSpecializationString = getArguments().getString("doctor_specialization");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_fragment,container,false);
        healthCareRepository.getIdOfDoctorWithEmail(doctorEmailString, doctorId -> doctor_Id = doctorId);
        setupRecyclerView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doctorName = view.findViewById(R.id.doctor_main_username);
        doctorEmail = view.findViewById(R.id.doctor_main_email);
        doctorAge = view.findViewById(R.id.doctor_main_age);
        doctorSpecialization = view.findViewById(R.id.doctor_main_specialization);

        setupTextViews();

        updateButton = view.findViewById(R.id.doctor_main_update);
        updateButton.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                Doctor doctor = new Doctor(doctorNameString,doctorEmailString,0,null);
                doctor.setId(doctor_Id);
                iActivityFragmentCommunication.loadUpdateDoctorFragment(doctor);
            }
        });
        Button loadPatientsButton = view.findViewById(R.id.doctor_load_button);
        loadPatientsButton.setOnClickListener(v -> {
            getPatients();
        });
        completeDoctor();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IActivityFragmentCommunication){
            iActivityFragmentCommunication = (IActivityFragmentCommunication) context;
        }
    }

    private void setupTextViews(){
        doctorName.setText(doctorNameString);
        doctorEmail.setText(doctorEmailString);
        doctorAge.setText(doctorAgeString);
        doctorSpecialization.setText(doctorSpecializationString);

    }

    private void setupRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.doctor_main_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(patientAdapter);
    }

    private void getPatients(){
        if (doctor_Id == 0){
            return;
        }
        healthCareRepository.getDoctorsWithPatients(doctor_Id, new HealthCareRepository.OnGetDoctorWithPatientsListener() {
            @Override
            public void onSuccess(List<DoctorsWithPatients> doctorsWithPatients) {
                patientList.clear();
                for (DoctorsWithPatients item : doctorsWithPatients){
                    patientList.addAll(item.patients);
                }
                patientAdapter.notifyDataSetChanged();
            }
        });
    }

    private void completeDoctor(){
        healthCareRepository.getDoctorWithEmail(doctorEmailString, new HealthCareRepository.OnGetDoctorWithEmailListener() {
            @Override
            public void onSuccess(Doctor doctor) {
                Doctor searchedDoctor = doctor;
                doctorNameString = searchedDoctor.getName();
                doctorName.setText(doctorNameString);
                doctorAgeString = searchedDoctor.getAge().toString();
                doctorAge.setText(doctorAgeString);
                doctorSpecializationString = searchedDoctor.getSpecialization();
                doctorSpecialization.setText(doctorSpecializationString);
            }
        });
    }
}
