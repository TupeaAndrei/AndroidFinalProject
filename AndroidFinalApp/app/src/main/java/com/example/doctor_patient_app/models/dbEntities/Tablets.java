package com.example.doctor_patient_app.models.dbEntities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tablets {
    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name="indications")
    private String indications;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="patient_id")
    private Integer patientId;

    public Tablets(Integer id, String name, String indications, String description, Integer patientId) {
        this.id = id;
        this.name = name;
        this.indications = indications;
        this.description = description;
        this.patientId = patientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}
