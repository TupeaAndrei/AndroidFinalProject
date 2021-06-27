package com.example.doctor_patient_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.adapters.DoctorAdviceAdapter;
import com.example.doctor_patient_app.adapters.TabletAdapter;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;
import com.example.doctor_patient_app.models.dbEntities.PatientWithAdvices;
import com.example.doctor_patient_app.models.dbEntities.PatientWithTablets;
import com.example.doctor_patient_app.models.dbEntities.Tablets;
import com.example.doctor_patient_app.repository.HealthCareRepository;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdvicesFragment extends Fragment {
    private IActivityFragmentCommunication iActivityFragmentCommunication;

    private ArrayList<DoctorAdvices> doctorAdvices = new ArrayList<DoctorAdvices>();
    private DoctorAdviceAdapter doctorAdviceAdapter = new DoctorAdviceAdapter(doctorAdvices);

    private HealthCareRepository healthCareRepository = new HealthCareRepository();

    private Integer patientId;

    public PatientFragment newInstance(){
        Bundle args = new Bundle();

        PatientFragment fragment = new PatientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            patientId = getArguments().getInt("patient_id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_advices_fragment,container,false);
        setupRecyclerView(view);
        getDoctorAdvices();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IActivityFragmentCommunication){
            iActivityFragmentCommunication = (IActivityFragmentCommunication) context;
        }
    }

    public void setupRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.doctor_advice_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(doctorAdviceAdapter);
    }

    public void getDoctorAdvices(){
        healthCareRepository.getPatientsWithAdvices(patientId, new HealthCareRepository.OnGetPatientWithAdvicesListener() {
            @Override
            public void onSuccess(List<PatientWithAdvices> patientWithAdvices) {
                doctorAdvices.clear();
                for (PatientWithAdvices item : patientWithAdvices){
                    doctorAdvices.addAll(item.advices);
                }
                doctorAdviceAdapter.notifyDataSetChanged();
            }
        });
    }
}
