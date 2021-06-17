package com.example.doctor_patient_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.models.DoctorAdviceElement;

import java.util.ArrayList;

public class DoctorAdviceAdapter extends RecyclerView.Adapter<DoctorAdviceAdapter.DoctorAdviceViewHolder> {

    private final ArrayList<DoctorAdviceElement> doctorAdviceList;

    public DoctorAdviceAdapter(ArrayList<DoctorAdviceElement> doctorAdviceList) {
        this.doctorAdviceList = doctorAdviceList;
    }

    @NonNull
    @Override
    public DoctorAdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_doctor_advice,parent,false);
        return new DoctorAdviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAdviceViewHolder holder, int position) {
        DoctorAdviceElement doctorAdviceElement = doctorAdviceList.get(position);
        holder.bind(doctorAdviceElement);
    }

    @Override
    public int getItemCount() {
        return doctorAdviceList.size();
    }

    class DoctorAdviceViewHolder extends RecyclerView.ViewHolder{
        private TextView doctorAdviceText;

        public DoctorAdviceViewHolder(View view){
            super(view);
            doctorAdviceText = view.findViewById(R.id.doctor_advice_text);
        }

        public void bind(DoctorAdviceElement doctorAdviceElement){
            doctorAdviceText.setText(doctorAdviceElement.getAdviceText());
        }
    }
}
