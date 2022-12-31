package com.example.turrefv2;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class LogicManagement {

    ActivityMainBinding binding;
    LogicHandler logicHandler;
    public static boolean isInverse;
    String Words;

    public LogicManagement(ActivityMainBinding binding, LogicHandler logicHandler) {
        this.binding = binding;
        this.logicHandler = logicHandler;
    }

    public void beginLogic() {

        Words = logicHandler.ReadManagement();

        if(isInverse){
            binding.TextUpperDisplay.setText("Tap to show");
            binding.TextLowerDisplay.setText(Words.substring(Words.indexOf('=') + 1));
        }
        else {
            binding.TextLowerDisplay.setText("Tap to show");
            binding.TextUpperDisplay.setText(Words.substring(0, Words.indexOf('=')));
        }
    }

    public void endLogic() {

        if(isInverse) {
            binding.TextUpperDisplay.setText(Words.substring(0, Words.indexOf('=')));
        }
        else {
            binding.TextLowerDisplay.setText(Words.substring(Words.indexOf('=') + 1));
        }
    }

    public void switchDisplay() {
        if(isInverse) {
            binding.ButtonLowerDisplay.setEnabled(false);
            binding.TextLowerDisplay.setText(Words.substring(Words.indexOf('=') + 1));
            binding.ButtonUpperDisplay.setEnabled(true);
            binding.TextUpperDisplay.setText("Tap to show");
        }
        else {
            binding.ButtonUpperDisplay.setEnabled(false);
            binding.TextUpperDisplay.setText(Words.substring(0, Words.indexOf('=')));
            binding.ButtonLowerDisplay.setEnabled(true);
            binding.TextLowerDisplay.setText("Tap to show");
        }
    }

}
