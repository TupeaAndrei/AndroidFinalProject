package com.example.doctor_patient_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;

public class WelcomeFragment extends Fragment {
    private ImageView doctorIcon;
    private ImageView patientIcon;

    private IActivityFragmentCommunication iActivityFragmentCommunication;

    public WelcomeFragment newInstance(){
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        Bundle args = new Bundle();
        welcomeFragment.setArguments(args);
        return welcomeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.welcome_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doctorIcon = view.findViewById(R.id.image_icon_doctor);
        patientIcon = view.findViewById(R.id.image_icon_patient);
        doctorIcon.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                iActivityFragmentCommunication.loadInitialDoctorFragment();
            }
        });

        patientIcon.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                iActivityFragmentCommunication.loadInitialPatientFragment();
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
