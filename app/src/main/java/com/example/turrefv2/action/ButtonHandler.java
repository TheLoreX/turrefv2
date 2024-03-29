package com.example.turrefv2.action;


import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.turrefv2.R;
import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.logic.DataHandler;
import com.example.turrefv2.logic.FileManager;
import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.PathHandler;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.utils.PermissionHandler;


public class ButtonHandler implements View.OnClickListener {

    ActivityMainBinding binding;
    Context context;
    AnimHandler animHandler;
    WordHandler wordHandler;
    LogicHandler logicHandler;
    PathHandler pathHandler;
    PermissionHandler permissionHandler;
    DataHandler dataHandler;

    private boolean historyMode; // represents whether the word history is being turned
    public static View clickedView;


    public ButtonHandler(ActivityMainBinding binding, Context context, AnimHandler animHandler, PermissionHandler permissionHandler, PathHandler pathHandler, WordHandler wordHandler, LogicHandler logicHandler, DataHandler dataHandler) {
        this.binding = binding;
        this.context = context;
        this.pathHandler = pathHandler;
        this.wordHandler = wordHandler;
        this.logicHandler = logicHandler;
        this.permissionHandler = permissionHandler;
        this.animHandler = animHandler;
        this.dataHandler = dataHandler;
    }

    @Override
    public void onClick(View view) {
        clickedView = view;
        switch (view.getId()) {
        //homepage
            case R.id.ButtonHome:
                animHandler.moveToggle(binding.ButtonHome.getLeft()-58);
                animHandler.pageHandler((byte) 0,false);
                break;

            case R.id.ButtonList:
                animHandler.moveToggle(binding.ButtonList.getLeft()-64);
                break;

            case R.id.ButtonPlay:
                if(FileManager.isFileExist) {
                    animHandler.pageHandler((byte) 2, false);
                    logicHandler.beginLogic(true);
                }
                else Toast.makeText(context , "You haven't added any file yet", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ButtonInfo:
                animHandler.moveToggle(binding.ButtonInfo.getLeft()-62);
                break;

            case R.id.ButtonSettings:
                animHandler.moveToggle(binding.ButtonSettings.getLeft()-62);
                animHandler.pageHandler((byte) 4, false);
                break;

            case R.id.ButtonAdd:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if(!Environment.isExternalStorageManager()) {
                        permissionHandler.getBroadPermission(pathHandler);
                    }
                    else {
                        pathHandler.pathReceiver();
                    }
                }
                else {
                    pathHandler.pathReceiver();
                }

                dataHandler.createMainDir();
                dataHandler.createRecentListDir();
                LogicHandler.countSpin = 0;
                AnimHandler.isTolerance = true;
                break;

        //wordpage
            case R.id.ButtonBack:
                animHandler.pageHandler((byte) 2, true);
                binding.midPanelPW.clearAnimation();
                binding.wordshowPanelPW.clearAnimation();
                break;

            case R.id.ButtonReplay:
                // changes how ReplayButton works depending on the history-mode activity
                SetClickState(true);
                if (!historyMode) {
                    logicHandler.beginLogic(false);
                }
                else {
                    logicHandler.arrangeDisplay();
                    historyMode = false;
                }
                break;

            case R.id.ButtonClue:
                String[] separatedWords = logicHandler.Words.split("=");
                if(!LogicHandler.isInverse) {
                    Toast.makeText(context, "Clue: " + (separatedWords[1] = separatedWords[1].trim()).substring(0, logicHandler.countClue > separatedWords[1].length() ? separatedWords[1].length() : logicHandler.countClue), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Clue: " + (separatedWords[0] = separatedWords[0].trim()).substring(0, logicHandler.countClue > separatedWords[0].length() ? separatedWords[0].length() : logicHandler.countClue), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.ButtonUpperDisplay:
            case R.id.ButtonLowerDisplay:
                logicHandler.endLogic();
                break;

            case R.id.ButtonSlider:
                animHandler.toggleInfo();
                break;

            case R.id.ButtonReturn:
                historyMode = true;
                SetClickState(false);
                logicHandler.historyLogic();
                break;
            case R.id.ButtonReset:
                WordHandler.selectedLine = 0;
                binding.TextSpinCount.setText(String.valueOf(LogicHandler.countSpin = 0));
                logicHandler.beginLogic(false);
                Toast.makeText(context, "The list has been reset", Toast.LENGTH_SHORT).show();
                break;

        //settingspage
            case R.id.ButtonInfoRandomMode:
                animHandler.openInfoBoxes((byte) 0);
                break;

            case R.id.ButtonInfoRepetition:
                animHandler.openInfoBoxes((byte) 1);
                break;

            case R.id.ButtonInfoClue:
                animHandler.openInfoBoxes((byte) 2);
                break;
            }

        }

    // sets the click state of DisplayButtons on desire
    private void SetClickState(boolean shouldBeClickable){

        if (shouldBeClickable) {
            if (!(binding.ButtonUpperDisplay.isClickable() && binding.ButtonUpperDisplay.isClickable())) {
                binding.ButtonUpperDisplay.setClickable(true);
                binding.ButtonLowerDisplay.setClickable(true);
            }
        }
        else{
            if (binding.ButtonUpperDisplay.isClickable() && binding.ButtonUpperDisplay.isClickable()) {
                binding.ButtonUpperDisplay.setClickable(false);
                binding.ButtonLowerDisplay.setClickable(false);
            }
        }
    }
}
