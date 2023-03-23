package com.example.turrefv2.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MinorDataHandler {

    SharedPreferences sharedPreferences;
    Context context;

    public MinorDataHandler(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public void storeMinorData(String Key, String Value) {
        SharedPreferences.Editor valueEditor = sharedPreferences.edit();
        valueEditor.putString(Key, Value);
        valueEditor.apply();
    }


}




