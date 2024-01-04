package com.example.turrefv2.logic;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.turrefv2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class LogicHandler {

    Context context;
    ActivityMainBinding binding;
    WordHandler wordHandler;

    public LogicHandler(ActivityMainBinding binding, Context context, WordHandler wordHandler) {
        this.binding = binding;
        this.context = context;
        this.wordHandler = wordHandler;
    }

    public static boolean initiationStatus, isInverse, isRandom, allowRepetition, forbidRepetition;
    public static int countSpin = 0, countClue = 1, wordLimit = 20;
    private int currentWord;
    private ArrayList<String> wordHistory = new ArrayList<>(wordLimit); // stores up the displayed words
    public String Words;

    public void beginLogic(boolean isPlayButton) {
        currentWord = wordHistory.size();
        if(wordHistory.size() == wordLimit) // removes the last element of the array if the capacity is reached
            wordHistory.remove(0);
        if(isPlayButton) { // defines whether beginLogic() is executed via PlayButton
            if(!initiationStatus) { // defines whether a spinning has already started
                binding.TextSpinCount.setText(String.valueOf(countSpin++));
                Words = wordHandler.ReadManagement();
                wordHistory.add(Words);
                initiationStatus = true;
            }
        }
        else {
            binding.TextSpinCount.setText(String.valueOf(countSpin++));
            Words = wordHandler.ReadManagement();
            wordHistory.add(Words);
        }
        arrangeDisplay();
    }

    public void endLogic() {
        if(isInverse) {
            binding.TextUpperDisplay.setText(Words.substring(0, Words.indexOf('=')));
        }
        else {
            binding.TextLowerDisplay.setText(Words.substring(Words.indexOf('=') + 1));
        }
    }

    public void historyLogic() {
        if (currentWord != 0) {
            currentWord--;
            binding.TextUpperDisplay.setText(wordHistory.get(currentWord).substring(0, wordHistory.get(currentWord).indexOf('=')));
            binding.TextLowerDisplay.setText(wordHistory.get(currentWord).substring(wordHistory.get(currentWord).indexOf('=') + 1));
        }
        else
            Toast.makeText(context, "Word history limit has been reached!", Toast.LENGTH_SHORT).show();
    }

    // arranges the words that are about to be shown based on the "isInverse" boolean
    private void arrangeDisplay() {
        if (isInverse) {
            binding.TextUpperDisplay.setText("Tap to show");
            binding.TextLowerDisplay.setText(Words.substring(Words.indexOf('=') + 1));
        }
        else {
            binding.TextLowerDisplay.setText("Tap to show");
            binding.TextUpperDisplay.setText(Words.substring(0, Words.indexOf('=')));
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
