package com.example.turrefv2;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class LogicHandler {

    ActivityMainBinding binding;
    WordHandler logicHandler;

    public LogicHandler(ActivityMainBinding binding, WordHandler logicHandler) {
        this.binding = binding;
        this.logicHandler = logicHandler;
    }

    String Words;
    public static boolean isInverse, isRun, isRandom, wordRepetition, forbidRepetition;
    public static int countSpin = 0, countClue = 1 ;

    public void beginLogic(boolean isPlayButton) {
        if(isPlayButton) {
            if(!isRun) {
                binding.TextSpinCount.setText(String.valueOf(countSpin++));
                Words = logicHandler.ReadManagement();
                isRun = true;
            }
        }
        else {
            binding.TextSpinCount.setText(String.valueOf(countSpin++));
            Words = logicHandler.ReadManagement();
        }

        if (isInverse) {
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

    public String getWords() {
        return Words;
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
