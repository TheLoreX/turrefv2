package com.example.turrefv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.action.ButtonHandler;
import com.example.turrefv2.action.SwitchHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.component.EditorWrapper;
import com.example.turrefv2.component.RecyclerAdapter;
import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.PathHandler;
import com.example.turrefv2.logic.RecentListManager;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.utils.MinorDataHandler;
import com.example.turrefv2.utils.PermissionHandler;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SharedPreferences storedVariables;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // pre executions
        storedVariables = getSharedPreferences("StoredVariables", Context.MODE_PRIVATE);


        // class definitions
        TouchHandler touchHandler = new TouchHandler(this);
        PathHandler pathHandler = new PathHandler(this);
        PermissionHandler permissionHandler = new PermissionHandler(this, this);
        WordHandler wordHandler = new WordHandler();
        LogicHandler logicHandler = new LogicHandler(binding , wordHandler);
        MinorDataHandler dataHandler = new MinorDataHandler(this, storedVariables);
        EditorWrapper editorWrapper = new EditorWrapper(binding, dataHandler);
        SwitchHandler switchHandler = new SwitchHandler(binding, logicHandler, dataHandler);
        ButtonHandler buttonHandler = new ButtonHandler(binding, this, permissionHandler, pathHandler, wordHandler, logicHandler);
        RecentListManager recentListManager = new RecentListManager(binding, this);

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
        preloadSettings();
        pathHandler.onIntentResult();
        permissionHandler.getPermission();
        recentListManager.showRecycler();
        AnimHandler.currentPage = binding.pageHome;

    }


    private void preloadSettings() {

            LogicHandler.isRandom = Boolean.parseBoolean(storedVariables.getString("isRandom", "true"));
            binding.SwitchRandomMode.setChecked(Boolean.parseBoolean(storedVariables.getString("isRandom", "true")));

            LogicHandler.wordRepetition = Boolean.parseBoolean(storedVariables.getString("wordRepetition", "true"));
            binding.SwitchRepetition.setChecked(Boolean.parseBoolean(storedVariables.getString("wordRepetition", "true")));

            LogicHandler.forbidRepetition = Boolean.parseBoolean(storedVariables.getString("forbidRepetition", "false"));
            binding.SwitchRepetitionLimit.setChecked(Boolean.parseBoolean(storedVariables.getString("forbidRepetition", "false")));

            LogicHandler.countClue = Integer.parseInt(storedVariables.getString("countClue", "1"));
            binding.EditClue.setHint(String.valueOf(storedVariables.getString("countClue", "1")));

            WordHandler.repetitionAmount = Integer.parseInt(storedVariables.getString("repetitionAmount", "1"));
            binding.EditRepetition.setHint(String.valueOf(storedVariables.getString("repetitionAmount", "1")));
    }
}

