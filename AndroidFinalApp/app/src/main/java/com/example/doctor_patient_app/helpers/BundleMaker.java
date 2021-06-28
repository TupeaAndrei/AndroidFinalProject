package com.example.doctor_patient_app.helpers;

import android.os.Bundle;

public class BundleMaker {
    private static final String notSpecifiedError = "Not specified!";

    public static Bundle setPatientBundle(String name, String email, String age,
                                    String height, String weight, String diagnostic)
    {
        Bundle args = new Bundle();
        if (age.equals("0")){
            age = notSpecifiedError;
        }
        if (height.equals("0")){
            height = notSpecifiedError;
        }
        if (weight.equals("0")){
            weight = notSpecifiedError;
        }
        if (diagnostic == null){
            diagnostic = notSpecifiedError;
        }
        args.putString("patient_name",name);
        args.putString("patient_email",email);
        args.putString("patient_age",age);
        args.putString("patient_height",height);
        args.putString("patient_weight",weight);
        args.putString("patient_diagnostic",diagnostic);
        return args;
    }

    public static Bundle setDoctorBundle(String username,String email,String age,String specialization){
        Bundle args = new Bundle();
        if (age.equals("0")){
            age = notSpecifiedError;
        }
        if (specialization == null){
            specialization = notSpecifiedError;
        }
        args.putString("doctor_name",username);
        args.putString("doctor_email",email);
        args.putString("doctor_age",age);
        args.putString("doctor_specialization",specialization);
        return args;
    }

    public static Bundle setBundleForUpdatePatient(Integer patientId,String name,String email,Integer doctorId){
        Bundle args = new Bundle();
        args.putInt("patient_id",patientId);
        args.putInt("doctor_id",doctorId);
        args.putString("patient_name",name);
        args.putString("patient_email",email);
        return args;
    }
}
