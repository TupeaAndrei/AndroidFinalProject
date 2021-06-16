package com.example.doctor_patient_app.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.doctor_patient_app.models.DoctorAdviceElement;

@Entity(tableName = "Doctor_Advices")
public class DoctorAdvices {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "advice_text")
    private String adviceText;

    @ColumnInfo(name = "patient_id")
    private Integer patientId;

    public DoctorAdvices(String adviceText, Integer patientId) {
        this.adviceText = adviceText;
        this.patientId = patientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdviceText() {
        return adviceText;
    }

    public void setAdviceText(String adviceText) {
        this.adviceText = adviceText;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public DoctorAdviceElement convert(){
        return new DoctorAdviceElement(adviceText,patientId);
    }
}
