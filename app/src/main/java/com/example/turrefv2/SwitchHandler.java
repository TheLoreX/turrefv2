package com.example.turrefv2;

import android.widget.CompoundButton;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class SwitchHandler implements CompoundButton.OnCheckedChangeListener {

    ActivityMainBinding binding;
    LogicManagement logicManagement;

    public SwitchHandler(ActivityMainBinding binding, LogicManagement logicManagement) {
        this.binding = binding;
        this.logicManagement = logicManagement;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.SwitchSide:
                if(b) {
                    LogicManagement.isInverse = true;
                    logicManagement.switchDisplay();

                }
                else {
                    LogicManagement.isInverse = false;
                    logicManagement.switchDisplay();
                }
                break;

        }
    }
}
