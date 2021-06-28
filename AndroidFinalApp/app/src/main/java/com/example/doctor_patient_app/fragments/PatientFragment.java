package com.example.doctor_patient_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.adapters.DoctorAdapter;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.interfaces.IAdapterDatabaseCommunication;
import com.example.doctor_patient_app.interfaces.IFragmentActivityCommunication;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

import java.util.ArrayList;
import java.util.List;

public class PatientFragment extends Fragment implements IFragmentActivityCommunication {
    private TextView patientName;
    private TextView patientEmail;
    private TextView patientAge;
    private TextView patientHeight;
    private TextView patientWeight;
    private TextView patientDiagnostic;

    private Button tabletsButton;
    private Button advicesButton;

    private Integer patient_id;
    private String patientNameString;
    private String patientEmailString;
    private String patientAgeString;
    private String patientHeightString;
    private String patientWeightString;
    private String patientDiagnosticString;
    private Integer doctorId;

    private IActivityFragmentCommunication iActivityFragmentCommunication;

    private ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
    private DoctorAdapter doctorAdapter = new DoctorAdapter(doctorList);

    private HealthCareRepository healthCareRepository = new HealthCareRepository();

    private ArrayList<Patient> patientList= new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;



    public PatientFragment newInstance(String name,String email,String age,String height,String weight,String diagnostic){
        Bundle args = new Bundle();

        PatientFragment fragment = new PatientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            patientNameString = getArguments().getString("patient_name");
            patientEmailString = getArguments().getString("patient_email");
            patientAgeString = getArguments().getString("patient_age");
            patientHeightString = getArguments().getString("patient_height");
            patientWeightString = getArguments().getString("patient_weight");
            patientDiagnosticString = getArguments().getString("patient_diagnostic");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_fragment,container,false);
        setupRecyclerView(view);
        getDoctors();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        patientName = view.findViewById(R.id.patient_name_main);
        patientEmail = view.findViewById(R.id.patient_email_main);
        patientAge = view.findViewById(R.id.patient_age_main);
        patientHeight = view.findViewById(R.id.patient_height_main);
        patientWeight = view.findViewById(R.id.patient_weight_main);
        patientDiagnostic = view.findViewById(R.id.patient_diagnostic_main);

        setupTextViews();

        tabletsButton = view.findViewById(R.id.patient_go_to_tablets);
        tabletsButton.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                iActivityFragmentCommunication.loadTabletsFragment(patient_id);
            }
        });
        advicesButton = view.findViewById(R.id.patient_go_to_advices);
        advicesButton.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                iActivityFragmentCommunication.loadDoctorAdvicesFragment(patient_id);
            }
        });
        Button updateButton = view.findViewById(R.id.update_patient_button);
        updateButton.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                Patient patient = new Patient(patientNameString,patientEmailString,null,0,0,0,doctorId);
                patient.setId(patient_id);
                iActivityFragmentCommunication.loadUpdatePatientFragment(patient);
            }
        });
        swipeRefreshLayout = view.findViewById(R.id.patient_fragm_swipe);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            completePatient();
            swipeRefreshLayout.setRefreshing(false);
        });
        completePatient();

    }

    private void setupTextViews(){
        patientName.setText(patientNameString);
        patientEmail.setText(patientEmailString);
        patientAge.setText(patientAgeString);
        patientHeight.setText(patientHeightString);
        patientWeight.setText(patientWeightString);
        patientDiagnostic.setText(patientDiagnosticString);

        healthCareRepository.getIdOfPatient(patientEmailString, patientId -> patient_id = patientId);
    }

    private void setupRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.patient_doctor_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(doctorAdapter);
    }

    private void getDoctors(){
        //remember to put a progress bar at the begining
        healthCareRepository.getAllDoctors(doctors -> {
            doctorList.clear();
            doctorList.addAll(doctors);
            doctorAdapter.notifyDataSetChanged();
            //remove progressbar
        });
    }
    @Override
    public void updatePatient(Integer id){
        String age = patientAgeString;
        String height = patientHeightString;
        String weight = patientWeightString;
        if (age.equals("Not specified!")){
            age = "0";
        }
        if (height.equals("Not specified!")){
            height = "0";
        }
        if (weight.equals("Not specified!")){
            weight="0";
        }
        Patient patient = new Patient(patientNameString,patientEmailString,patientDiagnosticString,
                Integer.parseInt(age),Integer.parseInt(height),Integer.parseInt(weight),id);
        patient.setId(patient_id);
        healthCareRepository.updatePatient(patient, () -> {
            Toast.makeText(getActivity().getApplicationContext(),"Patient succsesfully updated!",Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IActivityFragmentCommunication){
            iActivityFragmentCommunication = (IActivityFragmentCommunication) context;
        }
    }

    private void completePatient(){
        healthCareRepository.getPatientWithEmail(patientEmailString, new HealthCareRepository.OnGetPatientListener() {
            @Override
            public void onSuccess(List<Patient> patients) {
                patientList.addAll(patients);
                if (patientList.size() > 0) {
                    Patient searchedPatient = patientList.get(0);
                    patientNameString = searchedPatient.getName();
                    patientName.setText(patientNameString);
                    patientAgeString = searchedPatient.getAge().toString();
                    patientAge.setText(patientAgeString);
                    patientWeightString = searchedPatient.getWeight().toString();
                    patientWeight.setText(patientWeightString);
                    patientHeightString = searchedPatient.getHeight().toString();
                    patientHeight.setText(patientHeightString);
                    if (searchedPatient.getDiagnostic() != null) {
                        patientDiagnosticString = searchedPatient.getDiagnostic();
                        patientDiagnostic.setText(patientDiagnosticString);
                    }
                    doctorId = searchedPatient.getDoctorId();
                }
            }
        });
    }
}
