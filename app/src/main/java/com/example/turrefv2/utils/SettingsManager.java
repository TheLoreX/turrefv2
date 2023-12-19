package com.example.turrefv2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.logic.DataHandler;
import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.WordHandler;

public class SettingsManager {

    private static SharedPreferences storedVariables;
    ActivityMainBinding binding;
    Context context;

    public SettingsManager(ActivityMainBinding binding, Context context) {
        this.context = context;
        this.binding = binding;
        storedVariables = context.getSharedPreferences("StoredVariables", Context.MODE_PRIVATE);
    }

    public static void storeSettings(String Key, String Value) {
        SharedPreferences.Editor valueEditor = storedVariables.edit();
        valueEditor.putString(Key, Value);
        valueEditor.apply();

    }


    public void loadSettings() {

        LogicHandler.isRandom = Boolean.parseBoolean(storedVariables.getString("isRandom", "true"));
        binding.SwitchRandomMode.setChecked(Boolean.parseBoolean(storedVariables.getString("isRandom", "true")));

        LogicHandler.allowRepetition = Boolean.parseBoolean(storedVariables.getString("wordRepetition", "true"));
        binding.SwitchRepetition.setChecked(Boolean.parseBoolean(storedVariables.getString("wordRepetition", "true")));

        LogicHandler.forbidRepetition = Boolean.parseBoolean(storedVariables.getString("forbidRepetition", "false"));
        binding.SwitchRepetitionLimit.setChecked(Boolean.parseBoolean(storedVariables.getString("forbidRepetition", "false")));

        LogicHandler.countClue = Integer.parseInt(storedVariables.getString("countClue", "1"));
        binding.EditClue.setHint(String.valueOf(storedVariables.getString("countClue", "1")));

        WordHandler.repetitionAmount = Integer.parseInt(storedVariables.getString("repetitionAmount", "1"));
        binding.EditRepetition.setHint(String.valueOf(storedVariables.getString("repetitionAmount", "1")));

        DataHandler.mainDirPath = storedVariables.getString("mainDirPath", null);
        DataHandler.recentDirPath = storedVariables.getString("recentDirPath", null);
    }


}




