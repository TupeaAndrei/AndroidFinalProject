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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoctorLoginFragment extends Fragment {
    private View view;

    private EditText emailText;
    private EditText passwordText;

    private Button loginButton;

    private FirebaseAuth mAuth;

    private IActivityFragmentCommunication iActivityFragmentCommunication;

    public static DoctorLoginFragment newInstance(){
        Bundle args = new Bundle();

        DoctorLoginFragment fragment = new DoctorLoginFragment();
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
        view = inflater.inflate(R.layout.doctor_login,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailText = view.findViewById(R.id.email_doctor_login_text);
        passwordText = view.findViewById(R.id.password_login_doctor_text);
        loginButton = view.findViewById(R.id.login_doctor_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
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
        String password = passwordText.getText().toString();
        if (!Validators.isValidEmail(email)){
            emailText.setError("Invalid email!");
            return;
        }
        else{
            emailText.setError(null);
        }
        if (!Validators.isValidPassword(password)){
            passwordText.setError("Invalid password!");
            return;
        }else{
            passwordText.setError(null);
        }

        verifyFirebaseUser(email,password);

    }

    private void verifyFirebaseUser(String email,String password){
        if (getActivity() == null){
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(),
                        task -> {
                           if (task.isSuccessful()){
                               FirebaseUser user = mAuth.getCurrentUser();
                               Toast.makeText(getContext(),"Succesful login!",Toast.LENGTH_SHORT).show();
                               if (iActivityFragmentCommunication != null){

                               }
                           }else{
                               Toast.makeText(getContext(),"Login failed!",Toast.LENGTH_SHORT).show();
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
