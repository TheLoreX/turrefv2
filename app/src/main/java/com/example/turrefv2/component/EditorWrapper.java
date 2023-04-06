package com.example.turrefv2.component;


import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.turrefv2.R;
import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.utils.SettingsManager;

public class EditorWrapper implements TextView.OnEditorActionListener {

    ActivityMainBinding binding;
    SettingsManager settingsManager;

    public EditorWrapper(ActivityMainBinding binding, SettingsManager dataHandler) {
        this.binding = binding;
        this.settingsManager = dataHandler;
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {

        switch (textView.getId()) {
            case R.id.EditRepetition:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    binding.EditRepetition.clearFocus();

                    if (!binding.EditRepetition.getText().toString().isEmpty()) {
                        WordHandler.repetitionAmount = Integer.parseInt(String.valueOf(binding.EditRepetition.getText()));
                    }
                    binding.EditRepetition.setHint(String.valueOf(WordHandler.repetitionAmount));
                    settingsManager.storeSettings("repetitionAmount",String.valueOf(WordHandler.repetitionAmount));

                }
                break;
            case R.id.EditClue:
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    binding.EditClue.clearFocus();

                    if (!binding.EditClue.getText().toString().isEmpty()) {
                        LogicHandler.countClue = Integer.parseInt(String.valueOf(binding.EditClue.getText()));
                    }
                    binding.EditClue.setHint(String.valueOf(LogicHandler.countClue));
                    settingsManager.storeSettings("countClue",String.valueOf(LogicHandler.countClue));
                }
        }

        return false;
    }

}
