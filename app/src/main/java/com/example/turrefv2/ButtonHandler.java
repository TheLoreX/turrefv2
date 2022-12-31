package com.example.turrefv2;


import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class ButtonHandler extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    Activity activity;
    AnimHandler animHandler;
    LogicHandler logicHandler;
    LogicManagement logicManagement;
    PathHandler pathHandler;
    PermissionHandler permissionHandler;

    public ButtonHandler(ActivityMainBinding binding, Activity activity, PermissionHandler permissionHandler, PathHandler pathHandler, LogicHandler logicHandler, LogicManagement logicManagement) {
        this.binding = binding;
        this.activity = activity;
        this.permissionHandler = permissionHandler;
        this.pathHandler = pathHandler;
        this.logicHandler = logicHandler;
        this.logicManagement = logicManagement;
        animHandler = new AnimHandler(binding, activity);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
    //homepage
            case R.id.ButtonHome:
                animHandler.moveToggle(binding.ButtonHome.getLeft()-58);
                break;

            case R.id.ButtonList:
                animHandler.moveToggle(binding.ButtonList.getLeft()-64);
                break;

            case R.id.ButtonPlay:
                if(LogicHandler.isExist) {
                    binding.ButtonUpperDisplay.setEnabled(false);
                    animHandler.pageHandler((byte) 2, false);
                    logicManagement.beginLogic();
                }
                else Toast.makeText(activity , "You haven't added any file yet", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ButtonInfo:
                animHandler.moveToggle(binding.ButtonInfo.getLeft()-62);
                break;

            case R.id.ButtonSettings:
                animHandler.moveToggle(binding.ButtonSettings.getLeft()-62);
                break;

            case R.id.ButtonAdd:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if(!Environment.isExternalStorageManager()) permissionHandler.getBroadPermission(pathHandler);
                    else pathHandler.pathReceiver(this);
                }
                else pathHandler.pathReceiver(this);
                break;
    //wordpage
            case R.id.ButtonBack:
                animHandler.pageHandler((byte) 2, true);
                binding.midPanelPW.clearAnimation();
                binding.wordshowPanelPW.clearAnimation();
                break;

            case R.id.ButtonReplay:
                logicManagement.beginLogic();
                break;

            case R.id.ButtonUpperDisplay:
                logicManagement.endLogic();
                break;

            case R.id.ButtonLowerDisplay:
                logicManagement.endLogic();
                break;

            case R.id.TouchButtonSlider:
                animHandler.toggleInfo();
                break;

            }
        }

    public void attachTrigger() {
        logicHandler.isFileExist();
        logicHandler.LineCounter();
        animHandler.openAttach();
    }
}
