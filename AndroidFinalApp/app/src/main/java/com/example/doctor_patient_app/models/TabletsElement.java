package com.example.doctor_patient_app.models;

public class TabletsElement {
    private String name;
    private String description;
    private String indications;
    private Integer patientId;

    public TabletsElement(String name, String description, String indications, Integer patientId) {
        this.name = name;
        this.description = description;
        this.indications = indications;
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
