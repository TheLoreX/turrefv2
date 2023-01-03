package com.example.turrefv2;

import android.view.View;
import android.widget.CompoundButton;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class SwitchHandler implements CompoundButton.OnCheckedChangeListener {

    ActivityMainBinding binding;
    LogicHandler logicHandler;

    public SwitchHandler(ActivityMainBinding binding, LogicHandler logicHandler) {
        this.binding = binding;
        this.logicHandler = logicHandler;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {

            case R.id.SwitchSide:
                if(b) {
                    LogicHandler.isInverse = true;
                    logicHandler.switchDisplay();
                }
                else {
                    LogicHandler.isInverse = false;
                    logicHandler.switchDisplay();
                }
                break;

            case R.id.SwitchRandomMode:
                if(b) {
                    binding.CaseRandomMode.setText("on");
                    LogicHandler.isRandom = true;
                }
                else{
                    binding.CaseRandomMode.setText("off");
                    LogicHandler.isRandom = false;
                    WordHandler.selectedLine = -1;
                    WordHandler.repetitionList.clear();
                }
                break;

            case R.id.SwitchRepetition:
                if (b){
                    LogicHandler.wordRepetition = true;
                    binding.CaseRepetitionMode.setText("on");
                    binding.SwitchRepetitionLimit.setVisibility(View.VISIBLE);
                    binding.CaseRepetitionLimit.setVisibility(View.VISIBLE);
                    binding.EditRepetition.setVisibility(View.VISIBLE);
                }
                else{
                    LogicHandler.wordRepetition = false;
                    binding.CaseRepetitionMode.setText("off");
                    binding.SwitchRepetitionLimit.setVisibility(View.GONE);
                    binding.CaseRepetitionLimit.setVisibility(View.GONE);
                    binding.EditRepetition.setVisibility(View.GONE);
                }
                break;

            case R.id.SwitchRepetitionLimit:
                if (b) {
                    LogicHandler.forbidRepetition = true;
                    binding.EditRepetition.setVisibility(View.GONE);
                    binding.CaseRepetitionLimit.setText("repetition forbidden");
                }
                else {
                    LogicHandler.forbidRepetition = false;
                    binding.EditRepetition.setVisibility(View.VISIBLE);
                    binding.CaseRepetitionLimit.setText("repetition allowed");
                }
        }
    }
}
