package com.example.doctor_patient_app.helpers;

import android.text.TextUtils;
import android.util.Patterns;

public class Validators {

    public static boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password){
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    public static boolean isNumeric(String num){
        try{
            Integer.parseInt(num);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
