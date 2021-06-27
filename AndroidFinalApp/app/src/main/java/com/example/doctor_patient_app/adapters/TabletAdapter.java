package com.example.doctor_patient_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor_patient_app.R;
import com.example.doctor_patient_app.models.TabletsElement;
import com.example.doctor_patient_app.models.dbEntities.Tablets;

import java.util.ArrayList;

public class TabletAdapter extends RecyclerView.Adapter<TabletAdapter.TabletViewHolder> {

    private ArrayList<Tablets> tabletList;


    public TabletAdapter(ArrayList<Tablets> tabletList) {
        this.tabletList = tabletList;
    }

    @NonNull
    @Override
    public TabletViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_tablet,parent,false);
        return new TabletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabletViewHolder holder, int position) {
        Tablets tabletsElement = tabletList.get(position);
        holder.bind(tabletsElement);
    }

    @Override
    public int getItemCount() {
        return tabletList.size();
    }

    class TabletViewHolder extends RecyclerView.ViewHolder{
        private TextView tabletName;
        private TextView tabletDescription;
        private TextView tabletIndications;

        public TabletViewHolder(View view){
            super(view);

            tabletName = view.findViewById(R.id.tablet_name_text_view);
            tabletDescription = view.findViewById(R.id.tablet_description_text_view);
            tabletIndications = view.findViewById(R.id.tablet_indications_text);
        }

        public void bind(Tablets tabletsElement){
            tabletName.setText(tabletsElement.getName());
            tabletDescription.setText(tabletsElement.getDescription());
            tabletIndications.setText(tabletsElement.getIndications());
        }
    }
}
