package com.example.doctor_patient_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor_patient_app.models.DoctorElement;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private ArrayList<DoctorElement> doctorList;

    public DoctorAdapter(ArrayList<DoctorElement> doctorList){
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //inflate view here
        //instantiate de holder class and return him
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        DoctorElement doctorElement = doctorList.get(position);
        holder.bind(doctorElement);
    }

    @Override
    public int getItemCount() {
        return this.doctorList.size();
    }

    class DoctorViewHolder extends RecyclerView.ViewHolder{
        //add parameters when design is ready

        DoctorViewHolder(View view){
            super(view);
        }

        void bind(DoctorElement doctorElement){
            //set text for parameter from DoctorElement
        }
    }
}
