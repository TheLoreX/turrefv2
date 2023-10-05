package com.example.turrefv2.action;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.PathHandler;
import com.example.turrefv2.R;
import com.example.turrefv2.logic.RecentListManager;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.databinding.ActivityMainBinding;

public class AnimHandler implements Runnable {

    ActivityMainBinding binding;
    Context context;
    WordHandler wordHandler;

    public AnimHandler(ActivityMainBinding binding, Context context, WordHandler wordHandler) {
        this.binding = binding;
        this.context = context;
        this.wordHandler = wordHandler;
    }

    public static boolean toggleInfo, isTolerance, isAttachOn;
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

        wordHandler.WordCounter();
        wordHandler.LineCounter();
        binding.attachPanelPH.setVisibility(View.VISIBLE);
        //binding.TextFilename.setText(PathHandler.path.substring(PathHandler.path.lastIndexOf("/") + 1,PathHandler.path.indexOf(".")));
        binding.FrameAttach.startAnimation(AnimationUtils.loadAnimation(context, R.anim.attachin));
        binding.ViewCurrentlist.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.ViewCurrentlist.getAnimation().setStartOffset(300);
        binding.TextFilename.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextFilename.getAnimation().setStartOffset(500);
        binding.ViewPole.startAnimation(AnimationUtils.loadAnimation(context,R.anim.fadein));
        binding.ViewPole.getAnimation().setStartOffset(500);
        binding.TextWord.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextWord.getAnimation().setStartOffset(700);
        binding.TextWordCount.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextWordCount.getAnimation().setStartOffset(900);
        binding.TextLine.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextLine.getAnimation().setStartOffset(1100);
        binding.TextLinecount.setText(String.valueOf(WordHandler.LineCount));
        binding.TextLinecount.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
        binding.TextLinecount.getAnimation().setStartOffset(1300);
        binding.TextWordCount.setText(String.valueOf(WordHandler.WordCount));
        isAttachOn = true;

    }

    //sets the Recent List Members
    public void setRecentList() {
        if (WordHandler.isExist) {
            new RecentListManager(binding, context, this).setList();
        }
    }

    public void changeFileFromRecList(String path) {

        isTolerance = true;
        PathHandler.path = path;
        wordHandler.isDataExist();
        wordHandler.WordCounter();
        wordHandler.LineCounter();

        if (isAttachOn) {
            binding.ViewCurrentlist.startAnimation(AnimationUtils.loadAnimation(context, R.anim.bounce));
            binding.TextWordCount.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
            binding.TextWordCount.getAnimation().setStartOffset(400);
            binding.TextWordCount.setText(String.valueOf(WordHandler.WordCount));
            binding.TextLinecount.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
            binding.TextLinecount.getAnimation().setStartOffset(600);
            binding.TextLinecount.setText(String.valueOf(WordHandler.LineCount));
            binding.TextFilename.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fadein));
            binding.TextFilename.getAnimation().setStartOffset(800);
            binding.TextFilename.setText(path.substring(path.lastIndexOf("/") + 1, path.indexOf(".")));
        }
        else {
            openAttach();
        }

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
                    binding.TextSpinType.setText("Mode: " + ((LogicHandler.isRandom) ? "Random" : "Sequential"));
                    binding.TextSpinCapacity.setText("\\" + WordHandler.LineCount);
                    //binding.TextListName.setText("List: " + PathHandler.path.substring(PathHandler.path.lastIndexOf("/") + 1,PathHandler.path.indexOf(".")));
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
