package com.example.turrefv2.action;

import android.view.View;
import android.widget.CompoundButton;

import com.example.turrefv2.utils.MinorDataHandler;
import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.R;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.databinding.ActivityMainBinding;

public class SwitchHandler implements CompoundButton.OnCheckedChangeListener {

    ActivityMainBinding binding;
    LogicHandler logicHandler;
    MinorDataHandler dataHandler;

    public SwitchHandler(ActivityMainBinding binding, LogicHandler logicHandler, MinorDataHandler dataHandler) {
        this.binding = binding;
        this.dataHandler = dataHandler;
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
                    dataHandler.storeMinorData("isRandom","true");
                }
                else{
                    binding.CaseRandomMode.setText("off");
                    LogicHandler.isRandom = false;
                    WordHandler.selectedLine = -1;
                    WordHandler.repetitionList.clear();
                    dataHandler.storeMinorData("isRandom","false");
                }
                break;

            case R.id.SwitchRepetition:
                if (b){
                    LogicHandler.wordRepetition = true;
                    binding.CaseRepetitionMode.setText("on");
                    binding.SwitchRepetitionLimit.setVisibility(View.VISIBLE);
                    binding.CaseRepetitionLimit.setVisibility(View.VISIBLE);
                    binding.EditRepetition.setVisibility(View.VISIBLE);
                    dataHandler.storeMinorData("wordRepetition","true");
                }
                else{
                    LogicHandler.wordRepetition = false;
                    binding.SwitchRepetitionLimit.setChecked(false);
                    binding.CaseRepetitionMode.setText("off");
                    binding.SwitchRepetitionLimit.setVisibility(View.GONE);
                    binding.CaseRepetitionLimit.setVisibility(View.GONE);
                    binding.EditRepetition.setVisibility(View.GONE);
                    dataHandler.storeMinorData("wordRepetition","false");
                }
                break;

            case R.id.SwitchRepetitionLimit:
                if (b) {
                    LogicHandler.forbidRepetition = true;
                    binding.EditRepetition.setVisibility(View.GONE);
                    binding.CaseRepetitionLimit.setText("repetition forbidden");
                    dataHandler.storeMinorData("forbidRepetition","true");
                }
                else {
                    LogicHandler.forbidRepetition = false;
                    binding.EditRepetition.setVisibility(View.VISIBLE);
                    binding.CaseRepetitionLimit.setText("repetition allowed");
                    dataHandler.storeMinorData("forbidRepetition","false");
                }
        }
    }
}
