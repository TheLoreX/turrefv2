package com.example.turrefv2.action;


import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.PathHandler;
import com.example.turrefv2.utils.PermissionHandler;
import com.example.turrefv2.R;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.databinding.ActivityMainBinding;

public class ButtonHandler implements View.OnClickListener {

    Context context;
    ActivityMainBinding binding;
    AnimHandler animHandler;
    WordHandler wordHandler;
    LogicHandler logicHandler;
    PathHandler pathHandler;
    PermissionHandler permissionHandler;


    public ButtonHandler(ActivityMainBinding binding, Context context, PermissionHandler permissionHandler, PathHandler pathHandler, WordHandler wordHandler, LogicHandler logicHandler) {
        this.binding = binding;
        this.context = context;
        this.pathHandler = pathHandler;
        this.wordHandler = wordHandler;
        this.logicHandler = logicHandler;
        this.permissionHandler = permissionHandler;
        animHandler = new AnimHandler(binding, context);
    }

    public static View clickedView;

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
                if(WordHandler.isExist) {
                    if(!LogicHandler.isRun) binding.ButtonUpperDisplay.setEnabled(false);
                    animHandler.pageHandler((byte) 2, false);
                    logicHandler.beginLogic(true);
                    binding.TextSpinCapacity.setText("\\" + WordHandler.LineCount);
                    binding.TextListName.setText("List: " + PathHandler.path.substring(1,PathHandler.path.indexOf('.')));
                    if(LogicHandler.isRandom) binding.TextSpinType.setText("Mode: Random");
                    else binding.TextSpinType.setText("Mode: Sequential");
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
                        pathHandler.pathReceiver(animHandler, wordHandler); }
                }
                else {
                    pathHandler.pathReceiver(animHandler, wordHandler);
                }
                LogicHandler.countSpin = 0;
                break;

        //wordpage
            case R.id.ButtonBack:
                animHandler.pageHandler((byte) 2, true);
                binding.midPanelPW.clearAnimation();
                binding.wordshowPanelPW.clearAnimation();
                break;

            case R.id.ButtonReplay:
                logicHandler.beginLogic(false);
                break;
            case R.id.ButtonClue:
                if(!LogicHandler.isInverse) {
                    Toast.makeText(context, "Clue : "+ logicHandler.getWords().substring(logicHandler.getWords().indexOf("=") + 2, logicHandler.getWords().indexOf("=") + 2 + LogicHandler.countClue),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Clue : "+ logicHandler.getWords().substring(0, LogicHandler.countClue),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ButtonUpperDisplay:
                logicHandler.endLogic();
                break;

            case R.id.ButtonLowerDisplay:
                logicHandler.endLogic();
                break;

            case R.id.TouchButtonSlider:
                animHandler.toggleInfo();
                break;

            case R.id.ButtonReset:
                WordHandler.selectedLine = 0;
                WordHandler.repetitionList.clear();
                LogicHandler.countSpin = 0;
                binding.TextSpinCount.setText(String.valueOf(LogicHandler.countSpin));
                logicHandler.beginLogic(false);
                Toast.makeText(context, "List was reset", Toast.LENGTH_SHORT).show();
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

}
