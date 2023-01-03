package com.example.turrefv2;


import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class ButtonHandler implements View.OnClickListener {

    Activity activity;
    ActivityMainBinding binding;
    AnimHandler animHandler;
    WordHandler wordHandler;
    LogicHandler logicHandler;
    PathHandler pathHandler;
    PermissionHandler permissionHandler;


    public ButtonHandler(ActivityMainBinding binding, Activity activity, PermissionHandler permissionHandler, PathHandler pathHandler, WordHandler wordHandler, LogicHandler logicHandler) {
        this.binding = binding;
        this.activity = activity;
        this.permissionHandler = permissionHandler;
        this.pathHandler = pathHandler;
        this.wordHandler = wordHandler;
        this.logicHandler = logicHandler;
        animHandler = new AnimHandler(binding, activity);
    }

    public static View clickedView;

    @Override
    public void onClick(View view) {
        clickedView = view;
        switch (view.getId()) {
        //homepage
            case R.id.ButtonHome:
                animHandler.moveToggle(binding.ButtonHome.getLeft()-58);
                animHandler.pageHandler((byte) 0,false);
                break;

            case R.id.ButtonList:
                animHandler.moveToggle(binding.ButtonList.getLeft()-64);
                break;

            case R.id.ButtonPlay:
                if(WordHandler.isExist) {
                    if(!LogicHandler.isRun) binding.ButtonUpperDisplay.setEnabled(false);
                    animHandler.pageHandler((byte) 2, false);
                    logicHandler.beginLogic(true);
                }
                else Toast.makeText(activity , "You haven't added any file yet", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ButtonInfo:
                animHandler.moveToggle(binding.ButtonInfo.getLeft()-62);
                break;

            case R.id.ButtonSettings:
                animHandler.moveToggle(binding.ButtonSettings.getLeft()-62);
                animHandler.pageHandler((byte) 4, false);
                break;

            case R.id.ButtonAdd:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if(!Environment.isExternalStorageManager()) permissionHandler.getBroadPermission(pathHandler);
                    else pathHandler.pathReceiver(animHandler, wordHandler);
                }
                else pathHandler.pathReceiver(animHandler, wordHandler);
                break;

        //wordpage
            case R.id.ButtonBack:
                animHandler.pageHandler((byte) 2, true);
                binding.midPanelPW.clearAnimation();
                binding.wordshowPanelPW.clearAnimation();
                break;

            case R.id.ButtonReplay:
                logicHandler.beginLogic(false);
                break;

            case R.id.ButtonUpperDisplay:
                logicHandler.endLogic();
                break;

            case R.id.ButtonLowerDisplay:
                logicHandler.endLogic();
                break;

            case R.id.TouchButtonSlider:
                animHandler.toggleInfo();
                break;

        //settingspage
            case R.id.ButtonInfoRandomMode:
                animHandler.openInfoBoxes((byte) 0);
                break;

            case R.id.ButtonInfoRepetition:
                animHandler.openInfoBoxes((byte) 1);
                break;
            }

        }
}
