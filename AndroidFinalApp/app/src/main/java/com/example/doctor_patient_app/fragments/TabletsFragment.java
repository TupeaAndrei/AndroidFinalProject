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
import com.example.doctor_patient_app.adapters.TabletAdapter;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.models.dbEntities.PatientWithTablets;
import com.example.doctor_patient_app.models.dbEntities.Tablets;
import com.example.doctor_patient_app.repository.HealthCareRepository;

import java.util.ArrayList;
import java.util.List;

public class TabletsFragment extends Fragment {
    private IActivityFragmentCommunication iActivityFragmentCommunication;

    private ArrayList<Tablets> tabletList = new ArrayList<Tablets>();
    private TabletAdapter tabletAdapter = new TabletAdapter(tabletList);

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
        View view = inflater.inflate(R.layout.tablets_fragment,container,false);
        setupRecyclerView(view);
        getTablets();
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
        RecyclerView recyclerView = view.findViewById(R.id.tablet_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(tabletAdapter);
    }

    public void getTablets(){
        healthCareRepository.getPatientsWithTablets(patientId, patientsWithTablets -> {
            tabletList.clear();
            for(PatientWithTablets patientWithTablets : patientsWithTablets){
                tabletList.addAll(patientWithTablets.tablets);
            }
            tabletAdapter.notifyDataSetChanged();
        });
    }
}
