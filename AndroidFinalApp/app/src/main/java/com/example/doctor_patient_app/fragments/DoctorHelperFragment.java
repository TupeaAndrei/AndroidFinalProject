package com.example.doctor_patient_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.models.dbEntities.DoctorAdvices;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.models.dbEntities.Tablets;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;

public class DoctorHelperFragment extends Fragment {
    private EditText diagnosticEdit;
    private EditText adviceEditText;
    private EditText nameEdit;
    private EditText indicationsEdit;
    private EditText descriptionEdit;

    private Integer patientId;
    private String patientName;
    private String patientEmail;
    private String patientDiagnostic;
    private Integer patientAge;
    private Integer patientHeight;
    private Integer patientWeight;
    private Integer doctorId;

    private HealthCareRepository healthCareRepository = new HealthCareRepository();

    private static DoctorHelperFragment newInstance(){
        Bundle args = new Bundle();

        DoctorHelperFragment fragment = new DoctorHelperFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            patientId = getArguments().getInt("patient_id");
            patientName = getArguments().getString("patient_name");
            patientEmail = getArguments().getString("patient_email");
            patientDiagnostic = getArguments().getString("patient_diagnostic");
            patientAge = getArguments().getInt("patient_age");
            patientHeight = getArguments().getInt("patient_height");
            patientWeight = getArguments().getInt("patient_weight");
            doctorId = getArguments().getInt("doctor_id");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doctor_helper_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        diagnosticEdit = view.findViewById(R.id.patient_diagnostic_update_edit);
        adviceEditText = view.findViewById(R.id.advice_text_insert_edit);
        nameEdit = view.findViewById(R.id.tablet_name_insert_edit);
        indicationsEdit = view.findViewById(R.id.tablet_indications_insert_edit);
        descriptionEdit = view.findViewById(R.id.tablet_description_insert_edit);
        Button enterDiagnosticButton = view.findViewById(R.id.enter_diagnostic_button);
        Button enterAdviceButton = view.findViewById(R.id.enter_advice_button);
        Button enterTabletButton = view.findViewById(R.id.tablet_button_enter);
        enterDiagnosticButton.setOnClickListener(v -> {
            validateDiagnostic();
        });
        enterAdviceButton.setOnClickListener(v -> {
            validateAdvice();
        });
        enterTabletButton.setOnClickListener(v -> {
            validateTablet();
        });
    }

    private void validateDiagnostic(){
        if (getView() == null){
            return;
        }
        String diagnosticString = diagnosticEdit.getText().toString();
        if (diagnosticString.isEmpty()){
            diagnosticEdit.setError("This field can't be empty!");
            return;
        }else{
            diagnosticEdit.setError(null);
        }
        updateDiagnostic(diagnosticString);
    }

    private void validateAdvice(){
        if (getView() == null){
            return;
        }
        String adText = adviceEditText.getText().toString();
        if (adText.isEmpty()){
            adviceEditText.setError("This field can't be empty!");
            return;
        }else{
            adviceEditText.setError(null);
        }
        insertAdvice(adText);
    }

    private void validateTablet(){
        if (getView() == null){
            return;
        }
        String name = nameEdit.getText().toString();
        String indications = indicationsEdit.getText().toString();
        String description = descriptionEdit.getText().toString();
        if (name.isEmpty()){
            nameEdit.setError("This field can't be empty!");
            return;
        }else{
            nameEdit.setError(null);
        }
        if (indications.isEmpty()){
            indicationsEdit.setError("This field can't be empty!");
            return;
        }else{
            indicationsEdit.setError(null);
        }
        if (description.isEmpty()){
            descriptionEdit.setError("This field can't be empty!");
            return;
        }else{
            descriptionEdit.setError(null);
        }
        insertTablet(name,indications,description);
    }

    private void updateDiagnostic(String diagnostic){
        Patient updatedPatient = new Patient(patientName,patientEmail,diagnostic,patientAge,
                patientHeight,patientWeight,doctorId);
        updatedPatient.setId(patientId);
        healthCareRepository.updatePatient(updatedPatient, () -> {
            Toast.makeText(getContext(),"Diagnostic added!",Toast.LENGTH_SHORT);
        });
    }

    private void insertAdvice(String adviceText){
        DoctorAdvices doctorAdvices = new DoctorAdvices(adviceText,patientId);
        healthCareRepository.insertDoctorAdvice(doctorAdvices, () -> {
            Toast.makeText(getContext(),"Advice has been inserted!",Toast.LENGTH_SHORT).show();
        });
    }

    private void insertTablet(String name,String indications,String description){
        Tablets tablets = new Tablets(name,description,indications,patientId);
        healthCareRepository.insertTablet(tablets, () -> {
            Toast.makeText(getContext(),"Tablet has been inserted!",Toast.LENGTH_SHORT).show();
        });
    }
}
