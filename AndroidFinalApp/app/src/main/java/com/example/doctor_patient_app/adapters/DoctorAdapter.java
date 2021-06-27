package com.example.doctor_patient_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.interfaces.IAdapterDatabaseCommunication;
import com.example.doctor_patient_app.models.DoctorElement;
import com.example.doctor_patient_app.models.dbEntities.Doctor;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private final ArrayList<Doctor> doctorList;
    private View view;

    private IAdapterDatabaseCommunication iAdapterDatabaseCommunication;

    public DoctorAdapter(ArrayList<Doctor> doctorList){
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.item_doctor,parent,false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctorElement = doctorList.get(position);
        holder.bind(doctorElement);
        ImageView subscribeButton = holder.doctorSubscribe;
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //subscribe the patient to this specific doctor
                if (iAdapterDatabaseCommunication != null){
                    iAdapterDatabaseCommunication.updateThisPatient(holder.doctorId);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.doctorList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getContext() instanceof IAdapterDatabaseCommunication){
            iAdapterDatabaseCommunication = (IAdapterDatabaseCommunication) recyclerView.getContext();
        }
    }


    class DoctorViewHolder extends RecyclerView.ViewHolder{
        private Integer doctorId;

        private final TextView doctorName;
        private final TextView doctorEmail;
        private final TextView doctorAge;
        private final TextView doctorSpecialization;
        private final ImageView doctorSubscribe;

        DoctorViewHolder(View view){

            super(view);
            doctorName = view.findViewById(R.id.doctor_name_text_view);
            doctorEmail = view.findViewById(R.id.doctor_email_text_view);
            doctorAge = view.findViewById(R.id.doctor_age_value);
            doctorSpecialization = view.findViewById(R.id.doctor_spec_text_view);
            doctorSubscribe = view.findViewById(R.id.subscribe_doctor_image);
        }

        void bind(Doctor doctorElement){
            doctorId = doctorElement.getId();
            if (doctorElement.getSpecialization() == null){
                doctorElement.setSpecialization("Not specified!");
            }
            doctorName.setText(doctorElement.getName());
            doctorEmail.setText(doctorElement.getEmail());
            doctorAge.setText(String.valueOf(doctorElement.getAge()));
            doctorSpecialization.setText(doctorElement.getSpecialization());
        }
    }
}
