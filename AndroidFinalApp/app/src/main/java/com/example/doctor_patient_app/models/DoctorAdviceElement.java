package com.example.doctor_patient_app.models;

public class DoctorAdviceElement {
    private String adviceText;
    private Integer patientId;

    public DoctorAdviceElement(String adviceText, Integer patientId) {
        this.adviceText = adviceText;
        this.patientId = patientId;
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
}
