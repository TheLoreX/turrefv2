package com.example.turrefv2;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.turrefv2.databinding.ActivityMainBinding;

import java.nio.file.Path;

public class AnimHandler implements Runnable{

    ActivityMainBinding binding;
    Context context;

    AnimHandler(ActivityMainBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public static boolean toggleInfo;
    public static ConstraintLayout currentPage;

    public void openInfoBoxes(byte sort) {
        switch (sort) {
            case 0:
                if(binding.InfoBoxRandomMode.getVisibility() != View.VISIBLE) {
                    binding.InfoBoxRandomMode.animate().withStartAction(this).setDuration(500).scaleX(300).scaleY(100).start();
                }
                else {
                    binding.InfoBoxRandomMode.animate().withEndAction(this).setDuration(500).scaleX(0).scaleY(0).start();
                }
                break;
            case 1:
                if (binding.InfoBoxRepetitionMode.getVisibility() != View.VISIBLE) {
                    binding.InfoBoxRepetitionMode.animate().withStartAction(this).setDuration(500).scaleX(300).scaleY(130).start();
                }
                else {
                    binding.InfoBoxRepetitionMode.animate().withEndAction(this).setDuration(500).scaleX(0).scaleY(0).start();
                }
                break;
            case 2:
                if (binding.InfoBoxClue.getVisibility() != View.VISIBLE) {
                    binding.InfoBoxClue.animate().withStartAction(this).setDuration(500).scaleX(300).scaleY(85).start();
                }
                else {
                    binding.InfoBoxClue.animate().withEndAction(this).setDuration(500).scaleX(0).scaleY(0).start();
                }
                break;
        }
    }

    public void moveToggle(int call){
        ObjectAnimator toggleAnim = ObjectAnimator.ofFloat(binding.ImageToggle, "translationX", call);
        toggleAnim.setDuration(200);
        toggleAnim.start();
        if(toggleAnim.isPaused()) toggleAnim.end();
    }

    public void openAttach() {

        binding.TextFilename.setText(PathHandler.path.substring(PathHandler.path.lastIndexOf("/") + 1,PathHandler.path.indexOf(".")));
        binding.FrameAttach.startAnimation(AnimationUtils.loadAnimation(context, R.anim.attachin));

        binding.ViewCurrentlist.setVisibility(View.VISIBLE);
        Animation imageCurrentList = AnimationUtils.loadAnimation(context, R.anim.fadein);
        imageCurrentList.setStartOffset(300);
        binding.ViewCurrentlist.startAnimation(imageCurrentList);

        binding.TextFilename.setVisibility(View.VISIBLE);
        Animation textFilename = AnimationUtils.loadAnimation(context, R.anim.fadein);
        textFilename.setStartOffset(500);
        binding.TextFilename.startAnimation(textFilename);

        binding.TextLine.setVisibility(View.VISIBLE);
        binding.TextLine.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextLine.getAnimation().setStartOffset(700);
        binding.TextWord.setVisibility(View.VISIBLE);
        binding.TextWord.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextWord.getAnimation().setStartOffset(1100);
        binding.TextLinecount.setText(String.valueOf(WordHandler.LineCount));
        binding.TextLinecount.setVisibility(View.VISIBLE);
        binding.TextLinecount.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextLinecount.getAnimation().setStartOffset(900);
        binding.TextWordCount.setText(String.valueOf(WordHandler.WordCount));
        binding.TextWordCount.setVisibility(View.VISIBLE);
        binding.TextWordCount.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextWordCount.getAnimation().setStartOffset(1300);
        binding.ViewPole.setVisibility(View.VISIBLE);
        binding.ViewPole.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fadein));
        binding.ViewPole.getAnimation().setStartOffset(500);

    }

    public void pageHandler(byte page, boolean back) {
        switch (page) {
            case 0:
                if(currentPage != binding.pageHome) {
                    currentPage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_right));
                    binding.pageHome.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
                    currentPage.setVisibility(View.GONE);
                    binding.pageHome.setVisibility(View.VISIBLE);
                    currentPage = binding.pageHome;
                }
                break;
            case 2:
                if (!back) {
                    currentPage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_left));
                    binding.palette.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_left));
                    binding.pageWord.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right));
                    currentPage.setVisibility(View.GONE);
                    binding.pageWord.setVisibility(View.VISIBLE);
                    binding.palette.setVisibility(View.GONE);
                    currentPage = binding.pageWord;
                } else {
                    binding.pageHome.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
                    binding.palette.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
                    currentPage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_right));
                    binding.pageHome.setVisibility(View.VISIBLE);
                    currentPage.setVisibility(View.GONE);
                    binding.palette.setVisibility(View.VISIBLE);
                    moveToggle(binding.ButtonHome.getLeft() - 58);
                    currentPage = binding.pageHome;
                }
                break;
            case 4:
                if(currentPage != binding.pageSettings) {
                    currentPage.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_out_left));
                    binding.pageSettings.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_right));
                    currentPage.setVisibility(View.GONE);
                    binding.pageSettings.setVisibility(View.VISIBLE);
                    currentPage = binding.pageSettings;
                }
                break;
        }
    }

    public void toggleInfo() {
            if(!toggleInfo) {
                ObjectAnimator midPanelin = ObjectAnimator.ofFloat(binding.midPanelPW, "translationY", -390).setDuration(400);
                midPanelin.start();
                ObjectAnimator movePanel = ObjectAnimator.ofFloat(binding.wordshowPanelPW, "translationY", -80).setDuration(400);
                movePanel.start();
                binding.TextSpinCount.animate().withStartAction(this).alphaBy(0).alpha(1).setDuration(400).start();
                binding.TextSpinCapacity.animate().withStartAction(this).alphaBy(0).alpha(1).setDuration(400).start();
                toggleInfo = true;
            }
            else {
                ObjectAnimator midPanelout = ObjectAnimator.ofFloat(binding.midPanelPW, "translationY", 0).setDuration(400);
                midPanelout.start();
                ObjectAnimator movePanel = ObjectAnimator.ofFloat(binding.wordshowPanelPW, "translationY", -0).setDuration(400);
                movePanel.start();
                binding.TextSpinCount.animate().withEndAction(this).alphaBy(1).alpha(0).setDuration(400).start();
                binding.TextSpinCapacity.animate().withEndAction(this).alphaBy(1).alpha(0).setDuration(400).start();
                toggleInfo = false;
            }
        }

    @Override
    public void run() {
        switch (ButtonHandler.clickedView.getId()) {
            case R.id.ButtonInfoRandomMode:
                if (binding.InfoBoxRandomMode.getVisibility() != View.VISIBLE) {
                    binding.InfoBoxRandomMode.setVisibility(View.VISIBLE);
                    binding.ButtonInfoRepetition.setEnabled(false);
                    binding.ButtonClue.setEnabled(false);
                }
                else {
                    binding.InfoBoxRandomMode.setVisibility(View.GONE);
                    binding.ButtonInfoRepetition.setEnabled(true);
                    binding.ButtonClue.setEnabled(true);
                }
                break;
            case R.id.ButtonInfoRepetition:
                if (binding.InfoBoxRepetitionMode.getVisibility() != View.VISIBLE) {
                    binding.InfoBoxRepetitionMode.setVisibility(View.VISIBLE);
                    binding.ButtonInfoRandomMode.setEnabled(false);
                    binding.ButtonClue.setEnabled(false);
                }
                else {
                    binding.InfoBoxRepetitionMode.setVisibility(View.GONE);
                    binding.ButtonInfoRandomMode.setEnabled(true);
                    binding.ButtonClue.setEnabled(true);
                }
                break;
            case R.id.ButtonInfoClue:
                if (binding.InfoBoxClue.getVisibility() != View.VISIBLE){
                    binding.InfoBoxClue.setVisibility(View.VISIBLE);
                    binding.ButtonInfoRandomMode.setEnabled(false);
                    binding.ButtonInfoRepetition.setEnabled(false);
                }
                else {
                    binding.InfoBoxClue.setVisibility(View.GONE);
                    binding.ButtonInfoRandomMode.setEnabled(true);
                    binding.ButtonInfoRepetition.setEnabled(true);
                }
                break;

            case R.id.TouchButtonSlider:
                if (!toggleInfo) {
                    binding.TextSpinCount.setVisibility(View.VISIBLE);
                    binding.TextSpinCapacity.setVisibility(View.VISIBLE);
                }
                else {
                    binding.TextSpinCount.setVisibility(View.GONE);
                    binding.TextSpinCapacity.setVisibility(View.GONE);
                }
                break;
        }
    }


}
