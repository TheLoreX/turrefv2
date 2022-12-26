package com.example.turrefv2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class AnimHandler {

    ActivityMainBinding binding;
    Activity activity;
    public static boolean toggleInfo;

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
        imageCurrentList.setStartOffset(100);
        binding.ImageCurrentlist.startAnimation(imageCurrentList);

        binding.TextFilename.setVisibility(View.VISIBLE);
        Animation textFilename = AnimationUtils.loadAnimation(activity, R.anim.fadein);
        textFilename.setStartOffset(200);
        binding.TextFilename.startAnimation(textFilename);

        binding.TextLinecount.setText("Lines :    " + MainActivity.countOfLine);
        binding.TextLinecount.setVisibility(View.VISIBLE);
        Animation textLinecount = AnimationUtils.loadAnimation(activity, R.anim.fadein);
        textFilename.setStartOffset(300);
        binding.TextLinecount.startAnimation(textLinecount);

    }

    public void pageHandler(byte page, boolean reverse) {
        switch (page) {
            case 2:
                if (!reverse) {
                    binding.pageHome.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_left));
                    binding.pageWord.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_right));
                    binding.pageHome.setVisibility(View.GONE);
                    binding.pageWord.setVisibility(View.VISIBLE);
                } else {
                    binding.pageHome.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_in_left));
                    binding.pageWord.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.slide_out_right));
                    binding.pageHome.setVisibility(View.VISIBLE);
                    binding.pageWord.setVisibility(View.GONE);
                    moveToggle(binding.ButtonHome.getLeft() - 58);
                }
        }
    }
    public void toggleInfo() {
            if(!toggleInfo) {
                binding.midPanelPW.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.infoin));
                binding.ButtonSlider.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.sliderfix));
                ObjectAnimator animator = ObjectAnimator.ofFloat(binding.TouchButtonSlider, "translationY", -390);
                animator.setDuration(550);
                animator.start();
            }
            else {

            }
        }
}
