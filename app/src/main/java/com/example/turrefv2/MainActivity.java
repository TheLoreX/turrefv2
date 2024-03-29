package com.example.turrefv2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.action.ButtonHandler;
import com.example.turrefv2.action.SwitchHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.component.EditorWrapper;
import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.logic.DataHandler;
import com.example.turrefv2.logic.FileManager;
import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.PathHandler;
import com.example.turrefv2.logic.RecentListManager;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.utils.PermissionHandler;
import com.example.turrefv2.utils.SettingsManager;

public class MainActivity extends AppCompatActivity {

    public static String appName = "Turref";
    ActivityMainBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // class definitions
        TouchHandler touchHandler = new TouchHandler(this);
        PermissionHandler permissionHandler = new PermissionHandler(this, this);
        FileManager fileManager = new FileManager(this);
        WordHandler wordHandler = new WordHandler(this, fileManager);
        LogicHandler logicHandler = new LogicHandler(binding , this, wordHandler);
        SettingsManager settingsManager = new SettingsManager(binding, this);
        EditorWrapper editorWrapper = new EditorWrapper(binding, settingsManager);
        SwitchHandler switchHandler = new SwitchHandler(binding, logicHandler, settingsManager);
        DataHandler dataHandler = new DataHandler();
        AnimHandler animHandler = new AnimHandler(binding, this, wordHandler, dataHandler, fileManager);
        PathHandler pathHandler = new PathHandler(this, animHandler);
        ButtonHandler buttonHandler = new ButtonHandler(binding, this, animHandler, permissionHandler, pathHandler, wordHandler, logicHandler, dataHandler);
        RecentListManager recentListManager = new RecentListManager(binding, this, animHandler, dataHandler);
        // listeners

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
            binding.ButtonSlider.setOnClickListener(buttonHandler);
            binding.ButtonSlider.setOnTouchListener(touchHandler);
            binding.ButtonReplay.setOnClickListener(buttonHandler);
            binding.ButtonReplay.setOnTouchListener(touchHandler);
            binding.ButtonClue.setOnClickListener(buttonHandler);
            binding.ButtonClue.setOnTouchListener(touchHandler);
            binding.ButtonUpperDisplay.setOnClickListener(buttonHandler);
            binding.ButtonUpperDisplay.setOnTouchListener(touchHandler);
            binding.ButtonLowerDisplay.setOnClickListener(buttonHandler);
            binding.ButtonLowerDisplay.setOnTouchListener(touchHandler);
            binding.SwitchSide.setOnCheckedChangeListener(switchHandler);
            binding.ButtonReturn.setOnClickListener(buttonHandler);
            binding.ButtonReturn.setOnTouchListener(touchHandler);
            binding.ButtonReset.setOnClickListener(buttonHandler);
            binding.ButtonReset.setOnTouchListener(touchHandler);

            // settingspage
            binding.SwitchRandomMode.setOnCheckedChangeListener(switchHandler);
            binding.SwitchRepetition.setOnCheckedChangeListener(switchHandler);
            binding.SwitchRepetitionLimit.setOnCheckedChangeListener(switchHandler);
            binding.ButtonInfoRandomMode.setOnClickListener(buttonHandler);
            binding.ButtonInfoRandomMode.setOnTouchListener(touchHandler);
            binding.ButtonInfoRepetition.setOnClickListener(buttonHandler);
            binding.ButtonInfoRepetition.setOnTouchListener(touchHandler);
            binding.ButtonInfoClue.setOnClickListener(buttonHandler);
            binding.ButtonInfoClue.setOnTouchListener(touchHandler);
            binding.EditRepetition.setOnEditorActionListener(editorWrapper);
            binding.EditClue.setOnEditorActionListener(editorWrapper);

        // executions
        settingsManager.loadSettings();
        pathHandler.onIntentResult();
        permissionHandler.getPermission();
        recentListManager.initiateRecycler();
        AnimHandler.currentPage = binding.pageHome;

    }
}

