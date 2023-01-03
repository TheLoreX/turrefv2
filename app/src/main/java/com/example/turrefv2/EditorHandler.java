package com.example.turrefv2;


import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class EditorHandler implements TextView.OnEditorActionListener {

    ActivityMainBinding binding;

    EditorHandler(ActivityMainBinding binding) {
        this.binding = binding;
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {

        switch (textView.getId()) {
            case R.id.EditRepetition:
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    binding.EditRepetition.clearFocus();

                    if (!binding.EditRepetition.getText().toString().isEmpty()) {
                        WordHandler.repetitionAmount = Integer.parseInt((String.valueOf(binding.EditRepetition.getText())));
                    }
                    binding.EditRepetition.setHint(String.valueOf(WordHandler.repetitionAmount));
                }
                break;
        }

        return false;
    }

}
