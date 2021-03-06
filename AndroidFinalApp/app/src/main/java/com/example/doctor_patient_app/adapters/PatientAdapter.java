package com.example.doctor_patient_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.interfaces.IActivityFragmentCommunication;
import com.example.doctor_patient_app.models.PatientElement;
import com.example.doctor_patient_app.models.dbEntities.Patient;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private ArrayList<Patient> patientList;

    private IActivityFragmentCommunication iActivityFragmentCommunication;

    public PatientAdapter(ArrayList<Patient> patientList){
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_patient,parent,false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patientElement = patientList.get(position);
        holder.bind(patientElement);
        holder.itemView.setOnClickListener(v -> {
            if (iActivityFragmentCommunication != null){
                Integer age = Integer.parseInt(holder.patientAge.getText().toString());
                Integer height = Integer.parseInt(holder.patientHeight.getText().toString());
                Integer weight = Integer.parseInt(holder.patientWeight.getText().toString());
                Integer doctorId = Integer.parseInt(holder.doctorId.toString());
                Patient patient = new Patient(holder.patientName.getText().toString(),holder.patientEmail.getText().toString(),
                        holder.patientDiagnostic.getText().toString(), age,height,weight,doctorId);
                patient.setId(holder.patientId);
                iActivityFragmentCommunication.loadDoctorHelperFragment(patient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getContext() instanceof IActivityFragmentCommunication){
            iActivityFragmentCommunication = (IActivityFragmentCommunication) recyclerView.getContext();
        }

    }

    class PatientViewHolder extends RecyclerView.ViewHolder{
        private Integer patientId;
        private Integer doctorId;
        private TextView patientName;
        private TextView patientEmail;
        private TextView patientAge;
        private TextView patientHeight;
        private TextView patientWeight;
        private TextView patientDiagnostic;

        public PatientViewHolder(View view){
            super(view);
            patientName = view.findViewById(R.id.patient_name_text_view);
            patientEmail = view.findViewById(R.id.patient_email_text_view);
            patientAge = view.findViewById(R.id.patient_age_value);
            patientHeight = view.findViewById(R.id.patient_height_value);
            patientWeight = view.findViewById(R.id.patient_weight_value);
            patientDiagnostic = view.findViewById(R.id.patient_diagnostic);
        }

        public void bind(Patient patientElement){
            patientId = patientElement.getId();
            doctorId = patientElement.getDoctorId();
            patientName.setText(patientElement.getName());
            patientEmail.setText(patientElement.getEmail());
            patientAge.setText(String.valueOf(patientElement.getAge()));
            patientHeight.setText(String.valueOf(patientElement.getHeight()));
            patientWeight.setText(String.valueOf(patientElement.getWeight()));
            patientDiagnostic.setText(patientElement.getDiagnostic());
        }
    }
}
