package com.example.doctor_patient_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.helpers.Validators;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class UpdateDoctorFragment extends Fragment {
    private EditText ageEditText;
    private EditText specEditText;

    private TextView usernameText;

    private Button doneButton;

    private String doctorUsername;
    private String doctorEmail;
    private Integer doctorId;

    private String newAgeString;
    private String newSpecString;

    private HealthCareRepository healthCareRepository = new HealthCareRepository();

    public static UpdateDoctorFragment newInstance(){
        Bundle args = new Bundle();

        UpdateDoctorFragment fragment = new UpdateDoctorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            doctorId = getArguments().getInt("doctor_id");
            doctorUsername = getArguments().getString("doctor_name");
            doctorEmail = getArguments().getString("doctor_email");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update_doctor,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameText = view.findViewById(R.id.doctor_update_username_text);
        usernameText.setText(doctorUsername);
        ageEditText = view.findViewById(R.id.update_doctor_edit_age);
        specEditText = view.findViewById(R.id.update_doctor_spec_edit);
        doneButton = view.findViewById(R.id.update_doctor_done_button);
        doneButton.setOnClickListener(v -> {
            validateInput();
        });
    }

    private void validateInput(){
        if (getView() == null){
            return;
        }
        newAgeString = ageEditText.getText().toString();
        newSpecString = specEditText.getText().toString();
        if (newAgeString.isEmpty()){
            ageEditText.setError("This field can't be empty!");
            return;
        }else{
            ageEditText.setError(null);
        }
        if (newSpecString.isEmpty()){
            specEditText.setError("This field can't be empty!");
            return;
        }else{
            specEditText.setError(null);
        }
        if (!Validators.isNumeric(newAgeString)){
            ageEditText.setError("Not a number!");
            return;
        }else{
            ageEditText.setError(null);
        }
        updateDoctor();
    }

    private void updateDoctor(){
        Integer age = Integer.parseInt(newAgeString);
        Doctor updatedDoctor = new Doctor(doctorUsername,doctorEmail,age,newSpecString);
        updatedDoctor.setId(doctorId);
        healthCareRepository.updateDoctor(updatedDoctor, new HealthCareRepositoryListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getContext(),"Doctor updated!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
