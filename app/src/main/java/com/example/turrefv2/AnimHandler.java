package com.example.turrefv2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.dynamicanimation.animation.DynamicAnimation;

import com.example.turrefv2.databinding.ActivityMainBinding;

import java.time.Duration;

public class AnimHandler {

    ActivityMainBinding binding;
    Activity activity;


    AnimHandler(ActivityMainBinding binding, Activity activity) {
        this.binding = binding;
        this.activity = activity;
    }

    public void moveToggle(int call){
        ObjectAnimator toggleAnim = ObjectAnimator.ofFloat(binding.ImageToggle, "translationX", call);
        toggleAnim.setDuration(200);
        toggleAnim.start();
        if(toggleAnim.isPaused()) toggleAnim.end();
    }

    public void openAttach() {
        binding.TextFilename.setText(MainActivity.path.substring(1,MainActivity.path.indexOf(".")));
        Animation frameAttach = AnimationUtils.loadAnimation(activity, R.anim.attachin);
        binding.FrameAttach.startAnimation(frameAttach);

        binding.ImageCurrentlist.setVisibility(View.VISIBLE);
        Animation imageCurrentList = AnimationUtils.loadAnimation(activity, R.anim.fadein);
        binding.ImageCurrentlist.startAnimation(imageCurrentList);

        binding.TextFilename.setVisibility(View.VISIBLE);
        Animation textFilename = AnimationUtils.loadAnimation(activity, R.anim.fadein);
        binding.TextFilename.startAnimation(textFilename);

        //binding.TextLinecount.setText();

    }
}
