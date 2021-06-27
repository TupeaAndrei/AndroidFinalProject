package com.example.doctor_patient_app.fragments;

import android.content.Context;
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
import com.example.doctor_patient_app.helpers.Validators;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.models.dbEntities.Doctor;
import com.example.doctor_patient_app.models.dbEntities.Patient;
import com.example.doctor_patient_app.repository.HealthCareRepository;
import com.example.doctor_patient_app.repository.HealthCareRepositoryListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class PatientRegisterFragment extends Fragment {
    private View view;

    private EditText usernameText;
    private EditText emailText;
    private EditText passwordText;

    private Button registerButton;

    private FirebaseAuth mAuth;

    private HealthCareRepository healthCareRepository = new HealthCareRepository();

    private IActivityFragmentCommunication iActivityFragmentCommunication;

    public static PatientRegisterFragment newInstance(){
        Bundle args = new Bundle();

        PatientRegisterFragment fragment = new PatientRegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            //go-to-home skip login
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.patient_register,container,false);
        //this function is called so that the database inspector can initialize (for debug use only)
        healthCareRepository.getAllDoctors(new HealthCareRepository.OnGetDoctorListener() {
            @Override
            public void onSuccess(List<Doctor> doctors) {

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usernameText = view.findViewById(R.id.patient_name_text);
        emailText = view.findViewById(R.id.patient_email_text);
        passwordText = view.findViewById(R.id.patient_password_text);
        registerButton = view.findViewById(R.id.patient_register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });
    }



    private void validateInput(){
        if (getView() == null){
            return;
        }
        String email = emailText.getText().toString();
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        if (!Validators.isValidEmail(email)){
            emailText.setError("Invalid email!");
            return;
        }
        else{
            emailText.setError(null);
        }
        if (username.isEmpty()){
            usernameText.setError("This field can't be empty!");
            return;
        }
        else{
            usernameText.setError(null);
        }
        if (!Validators.isValidPassword(password)){
            passwordText.setError("Invalid password!");
            return;
        }else{
            passwordText.setError(null);
        }

        createFirebaseUser(email,password,username);

    }

    private void createFirebaseUser(String email,String password,String username){
        if (getActivity() == null){
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Patient patient = new Patient(username,email,null,0,0,0,0);
                            if (iActivityFragmentCommunication != null){
                                iActivityFragmentCommunication.loadMainPatientFragment(patient);
                            }
                            healthCareRepository.insertPatient(patient, new HealthCareRepositoryListener() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(getActivity(),"Patient added to database!",Toast.LENGTH_SHORT).show();
                                }
                            });
                            Toast.makeText(getContext(),"Authentification succesfull!",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(),"Authentification failed!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IActivityFragmentCommunication){
            iActivityFragmentCommunication = (IActivityFragmentCommunication) context;
        }
    }
}
