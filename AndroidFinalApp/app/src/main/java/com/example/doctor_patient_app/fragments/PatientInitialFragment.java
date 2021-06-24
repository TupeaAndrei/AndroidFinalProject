package com.example.doctor_patient_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;

public class PatientInitialFragment extends Fragment {
    private IActivityFragmentCommunication iActivityFragmentCommunication;

    public PatientInitialFragment newInstance(){
        Bundle args = new Bundle();

        PatientInitialFragment fragment = new PatientInitialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.patient_initial,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button registerButton = view.findViewById(R.id.select_patient_register);
        Button loginButton = view.findViewById(R.id.select_patient_login);
        registerButton.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                iActivityFragmentCommunication.loadPatientRegisterFragment();
            }
        });
        loginButton.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                iActivityFragmentCommunication.loadPatientLoginFragment();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IActivityFragmentCommunication){
            iActivityFragmentCommunication = (IActivityFragmentCommunication) context;
        }
    }
}
