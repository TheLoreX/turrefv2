package com.example.turrefv2;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.Gesture;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    GestureDetector gestureDetector;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // class definitions
        TouchHandler touchHandler = new TouchHandler(this);
        PathHandler pathHandler = new PathHandler(this, this);
        PermissionHandler permissionHandler = new PermissionHandler(this, this);
        WordHandler wordHandler = new WordHandler();
        LogicHandler logicHandler = new LogicHandler(binding , wordHandler);
        EditorHandler editorHandler = new EditorHandler(binding);
        ButtonHandler buttonHandler = new ButtonHandler(binding, this, permissionHandler, pathHandler, wordHandler, logicHandler);
        SwitchHandler switchHandler = new SwitchHandler(binding, logicHandler);

        // listeners
            gestureDetector = new GestureDetector(this, touchHandler);
            // homepage
            binding.ButtonHome.setOnClickListener(buttonHandler);
            binding.ButtonHome.setOnTouchListener(touchHandler);
            binding.ButtonList.setOnClickListener(buttonHandler);
            binding.ButtonList.setOnTouchListener(touchHandler);
            binding.ButtonPlay.setOnClickListener(buttonHandler);
            binding.ButtonPlay.setOnTouchListener(touchHandler);
            binding.ButtonInfo.setOnClickListener(buttonHandler);
            binding.ButtonInfo.setOnTouchListener(touchHandler);
            binding.ButtonSettings.setOnClickListener(buttonHandler);
            binding.ButtonSettings.setOnTouchListener(touchHandler);
            binding.ButtonAdd.setOnClickListener(buttonHandler);
            binding.ButtonAdd.setOnTouchListener(touchHandler);
            // wordpage
            binding.ButtonBack.setOnClickListener(buttonHandler);
            binding.ButtonBack.setOnTouchListener(touchHandler);
            binding.TouchButtonSlider.setOnClickListener(buttonHandler);
            binding.TouchButtonSlider.setOnTouchListener(touchHandler);
            binding.ButtonReplay.setOnClickListener(buttonHandler);
            binding.ButtonReplay.setOnTouchListener(touchHandler);
            binding.ButtonClue.setOnClickListener(buttonHandler);
            binding.ButtonClue.setOnTouchListener(touchHandler);
            binding.ButtonUpperDisplay.setOnClickListener(buttonHandler);
            binding.ButtonUpperDisplay.setOnTouchListener(touchHandler);
            binding.ButtonLowerDisplay.setOnClickListener(buttonHandler);
            binding.ButtonLowerDisplay.setOnTouchListener(touchHandler);
            binding.SwitchSide.setOnCheckedChangeListener(switchHandler);

            // settingspage
            binding.SwitchRandomMode.setOnCheckedChangeListener(switchHandler);
            binding.SwitchRepetition.setOnCheckedChangeListener(switchHandler);
            binding.SwitchRepetitionLimit.setOnCheckedChangeListener(switchHandler);
            binding.ButtonInfoRandomMode.setOnClickListener(buttonHandler);
            binding.ButtonInfoRandomMode.setOnTouchListener(touchHandler);
            binding.ButtonInfoRepetition.setOnClickListener(buttonHandler);
            binding.ButtonInfoRepetition.setOnTouchListener(touchHandler);
            binding.EditRepetition.setOnEditorActionListener(editorHandler);


        // executions
        pathHandler.onIntentResult();
        permissionHandler.getPermission();
        AnimHandler.currentPage = binding.pageHome;
        LogicHandler.isRandom = true;
        LogicHandler.wordRepetition = true;
    }
}

