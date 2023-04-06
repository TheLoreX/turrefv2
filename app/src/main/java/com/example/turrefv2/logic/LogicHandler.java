package com.example.turrefv2.logic;

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.databinding.ActivityMainBinding;

public class LogicHandler {

    ActivityMainBinding binding;
    WordHandler wordHandler;

    public LogicHandler(ActivityMainBinding binding, WordHandler wordHandler) {
        this.binding = binding;
        this.wordHandler = wordHandler;
    }

    public static boolean hasBegun, isInverse, isRandom, allowRepetition, forbidRepetition;
    public static int countSpin = 0, countClue = 1 ;
    public String Words;

    public void beginLogic(boolean isPlayButton) {
        if(isPlayButton || AnimHandler.isTolerance) {
            if(!(hasBegun = true) || AnimHandler.isTolerance) {
                binding.TextSpinCount.setText(String.valueOf(countSpin++));
                Words = wordHandler.ReadManagement();
                AnimHandler.isTolerance = false;
            }
        }
        else {
            binding.TextSpinCount.setText(String.valueOf(countSpin++));
            Words = wordHandler.ReadManagement();
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
