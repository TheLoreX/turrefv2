package com.example.turrefv2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.turrefv2.databinding.ActivityMainBinding;

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
        binding.FrameAttach.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.attachin));
        binding.FrameAttach.invalidate();
        binding.ImageCurrentList.setVisibility(View.VISIBLE);
        binding.ImageCurrentList.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.bounce));
        binding.ImageCurrentList.invalidate();
    }
}
