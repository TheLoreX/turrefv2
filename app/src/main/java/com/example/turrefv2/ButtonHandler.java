package com.example.turrefv2;


import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class ButtonHandler extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    AnimHandler animHandler;
    WordHandler wordHandler;
    PathHandler pathHandler;
    PermissionHandler permissionHandler;

    public ButtonHandler(ActivityMainBinding binding, Activity activity, PermissionHandler permissionHandler, PathHandler pathHandler) {
        this.binding = binding;
        this.permissionHandler = permissionHandler;
        this.pathHandler = pathHandler;
        animHandler = new AnimHandler(binding, activity);
        wordHandler = new WordHandler();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonHome:
                animHandler.moveToggle(binding.ButtonHome.getLeft()-58);
                break;
            case R.id.ButtonList:
                animHandler.moveToggle(binding.ButtonList.getLeft()-64);
                break;
            case R.id.ButtonPlay:
                animHandler.moveToggle(binding.ButtonPlay.getLeft()-66);
                animHandler.pageHandler((byte) 2, false);
                break;
            case R.id.ButtonInfo:
                animHandler.moveToggle(binding.ButtonInfo.getLeft()-62);
                break;
            case R.id.ButtonSettings:
                animHandler.moveToggle(binding.ButtonSettings.getLeft()-62);
                break;
            case R.id.ButtonAdd:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if(!Environment.isExternalStorageManager()) {
                        permissionHandler.getBroadPermission(pathHandler);
                    }
                    else {pathHandler.pathReceiver(this);}
                }
                else {pathHandler.pathReceiver(this);}
                break;
            case R.id.ButtonBack:
                animHandler.pageHandler((byte) 2, true);
                break;
            case R.id.TouchButtonSlider:
                animHandler.toggleInfo();
            }
        }

    public void attachTrigger() {
        wordHandler.LineCounter(MainActivity.path);
        animHandler.openAttach();
    }
}
