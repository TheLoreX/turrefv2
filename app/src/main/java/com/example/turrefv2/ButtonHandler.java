package com.example.turrefv2;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.databinding.PageWordBinding;

public class ButtonHandler extends AppCompatActivity implements View.OnClickListener {

    AnimHandler animate;
    ActivityMainBinding binding;
    Activity activity;


    public ButtonHandler(ActivityMainBinding binding, Activity activity) {
        this.binding = binding;
        this.activity = activity;
        animate = new AnimHandler(binding, activity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ButtonHome:
                animate.moveToggle(binding.ButtonHome.getLeft()-58);
                break;
            case R.id.ButtonList:
                animate.moveToggle(binding.ButtonList.getLeft()-64);
                animate.openAttach();

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
                PathHandler.pathReceiver(activity);
                break;
        }
    }


}
