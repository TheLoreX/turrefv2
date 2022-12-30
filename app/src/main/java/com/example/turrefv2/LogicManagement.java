package com.example.turrefv2;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class LogicManagement {

    ActivityMainBinding binding;
    LogicHandler logicHandler;
    public static boolean isInverse;

    public LogicManagement(ActivityMainBinding binding, LogicHandler logicHandler) {
        this.binding = binding;
        this.logicHandler = logicHandler;
    }

    public void printLogic(boolean isReverse) {

        String Words;
        Words = logicHandler.ReadManagement();

        if(!isReverse) {
            if (isInverse) {
                binding.TextUpperDisplay.setText("Tap to Show");
                binding.TextLowerDisplay.setText(Words.substring(Words.indexOf('=') + 1));
            } else {
                binding.TextLowerDisplay.setText("Tap to Show");
                binding.TextUpperDisplay.setText(Words.substring(0, Words.indexOf('=')));
            }
        }
        else {
            if (isInverse) {
                binding.TextLowerDisplay.setText("Tap to Show");
                binding.TextUpperDisplay.setText(Words.substring(Words.indexOf('=') + 1));
            } else {
                binding.TextUpperDisplay.setText("Tap to Show");
                binding.TextLowerDisplay.setText(Words.substring(0, Words.indexOf('=')));
            }
        }

    }

}
