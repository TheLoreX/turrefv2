package com.example.turrefv2;

import android.animation.ObjectAnimator;
import android.app.Activity;
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

        binding.TextFilename.setText(PathHandler.path.substring(1,PathHandler.path.indexOf(".")));
        Animation frameAttach = AnimationUtils.loadAnimation(activity, R.anim.attachin);
        binding.FrameAttach.startAnimation(frameAttach);

        binding.ViewCurrentlist.setVisibility(View.VISIBLE);
        Animation imageCurrentList = AnimationUtils.loadAnimation(activity, R.anim.fadein);
        imageCurrentList.setStartOffset(100);
        binding.ViewCurrentlist.startAnimation(imageCurrentList);

        binding.TextFilename.setVisibility(View.VISIBLE);
        Animation textFilename = AnimationUtils.loadAnimation(activity, R.anim.fadein);
        textFilename.setStartOffset(200);
        binding.TextFilename.startAnimation(textFilename);

        binding.TextLinecount.setText(String.valueOf(LogicHandler.LineCount));
        binding.TextLinecount.setVisibility(View.VISIBLE);
        binding.TextLine.setVisibility(View.VISIBLE);
        binding.TextLine.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fadein));
        binding.TextLine.getAnimation().setStartOffset(300);
        binding.TextLinecount.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fadein));
        binding.TextLinecount.getAnimation().setStartOffset(300);
        binding.ViewPole.setVisibility(View.VISIBLE);
        binding.ViewPole.startAnimation(AnimationUtils.loadAnimation(activity,R.anim.fadein));
        binding.ViewPole.getAnimation().setStartOffset(300);

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
                ObjectAnimator midPanelin = ObjectAnimator.ofFloat(binding.midPanelPW, "translationY", -390).setDuration(400);
                midPanelin.start();
                ObjectAnimator movePanel = ObjectAnimator.ofFloat(binding.wordshowPanelPW, "translationY", -80).setDuration(400);
                movePanel.start();
                toggleInfo = true;
            }
            else {
                ObjectAnimator midPanelout = ObjectAnimator.ofFloat(binding.midPanelPW, "translationY", 0).setDuration(400);
                midPanelout.start();
                ObjectAnimator movePanel = ObjectAnimator.ofFloat(binding.wordshowPanelPW, "translationY", -0).setDuration(400);
                movePanel.start();
                toggleInfo = false;
            }
        }
}
