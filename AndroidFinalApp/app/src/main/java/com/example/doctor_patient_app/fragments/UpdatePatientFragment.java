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
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class UpdatePatientFragment extends Fragment {

    private EditText ageEditText;
    private EditText heightEditText;
    private EditText weightEditText;

    private TextView usernameText;

    private Button doneButton;

    private String patientName;
    private String patientEmail;
    private Integer patientId;
    private Integer doctorId;

    private String newAgeString;
    private String newHeightString;
    private String newWeightString;

    private HealthCareRepository healthCareRepository = new HealthCareRepository();

    public static UpdatePatientFragment newInstance(){
        Bundle args = new Bundle();

        UpdatePatientFragment fragment = new UpdatePatientFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            patientId = getArguments().getInt("patient_id");
            doctorId = getArguments().getInt("doctor_id");
            patientName = getArguments().getString("patient_name");
            patientEmail = getArguments().getString("patient_email");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.update_patient,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameText = view.findViewById(R.id.patient_update_name_text);
        usernameText.setText(patientName);
        ageEditText = view.findViewById(R.id.patient_update_age_edit);
        heightEditText = view.findViewById(R.id.patient_update_height_edit);
        weightEditText = view.findViewById(R.id.patient_update_weight_edit);
        doneButton = view.findViewById(R.id.patient_update_done);
        doneButton.setOnClickListener(v -> {
            validateInput();
        });
    }

    private void validateInput(){
        if (getView() == null){
            return;
        }
        newAgeString = ageEditText.getText().toString();
        newHeightString = heightEditText.getText().toString();
        newWeightString = weightEditText.getText().toString();
        if (newAgeString.isEmpty()){
            ageEditText.setError("This field can't be empty!");
            return;
        }else{
            ageEditText.setError(null);
        }
        if(!Validators.isNumeric(newAgeString)){
            ageEditText.setError("This field is not a number!");
            return;
        }else{
            ageEditText.setError(null);
        }
        if (newHeightString.isEmpty()){
            heightEditText.setError("This field can't be empty!");
            return;
        }else{
            heightEditText.setError(null);
        }
        if(!Validators.isNumeric(newHeightString)){
            heightEditText.setError("This field is not a number!");
            return;
        }else{
            heightEditText.setError(null);
        }
        if (newWeightString.isEmpty()){
            weightEditText.setError("This field can't be empty!");
            return;
        }else{
            weightEditText.setError(null);
        }
        if(!Validators.isNumeric(newWeightString)){
            weightEditText.setError("This field is not a number!");
            return;
        }else{
            weightEditText.setError(null);
        }
        updatePatient();
    }

    private void updatePatient(){
        Integer age = Integer.parseInt(newAgeString);
        Integer height = Integer.parseInt(newHeightString);
        Integer weight = Integer.parseInt(newWeightString);
        Patient updatedPatient = new Patient(patientName,patientEmail,null,age,
                height,weight,doctorId);
        updatedPatient.setId(patientId);
        healthCareRepository.updatePatient(updatedPatient, () -> Toast.makeText(getContext(),"Patient updated!",Toast.LENGTH_SHORT).show());
    }
}
