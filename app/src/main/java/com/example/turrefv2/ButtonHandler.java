package com.example.turrefv2;


import android.app.Activity;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.databinding.ActivityMainBinding;

import java.io.IOException;

public class ButtonHandler extends AppCompatActivity implements View.OnClickListener {


    ActivityMainBinding binding;
    Activity activity;
    AnimHandler animate;
    WordHandler wordHandler;

    public ButtonHandler(ActivityMainBinding binding, Activity activity) {
        this.binding = binding;
        this.activity = activity;
        animate = new AnimHandler(binding, activity);
        wordHandler= new WordHandler();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonHome:

                animate.moveToggle(binding.ButtonHome.getLeft()-58);
                break;
            case R.id.ButtonList:
                animate.moveToggle(binding.ButtonList.getLeft()-64);
                break;
            case R.id.ButtonPlay:
                animate.moveToggle(binding.ButtonPlay.getLeft()-66);
                break;
            case R.id.ButtonInfo:
                animate.moveToggle(binding.ButtonInfo.getLeft()-62);
                break;
            case R.id.ButtonSettings:
                animate.moveToggle(binding.ButtonSettings.getLeft()-62);
                break;
            case R.id.ButtonAdd:
                PathHandler pathHandler = new PathHandler();
                pathHandler.pathReceiver();
                break;
        }
    }

    public void notepadInform(String path) {
        wordHandler.LineCounter(path);
    }
}
