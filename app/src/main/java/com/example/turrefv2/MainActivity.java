package com.example.turrefv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.action.ButtonHandler;
import com.example.turrefv2.action.SwitchHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.component.EditText;
import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.logic.LogicHandler;
import com.example.turrefv2.logic.PathHandler;
import com.example.turrefv2.logic.WordHandler;
import com.example.turrefv2.utils.DataHandler;
import com.example.turrefv2.utils.PermissionHandler;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // class definitions
        TouchHandler touchHandler = new TouchHandler(this);
        PathHandler pathHandler = new PathHandler(this);
        PermissionHandler permissionHandler = new PermissionHandler(this, this);
        WordHandler wordHandler = new WordHandler();
        LogicHandler logicHandler = new LogicHandler(binding , wordHandler);
        DataHandler dataHandler = new DataHandler(this);
        EditText editorHandler = new EditText(binding, dataHandler);
        SwitchHandler switchHandler = new SwitchHandler(binding, logicHandler, dataHandler);
        ButtonHandler buttonHandler = new ButtonHandler(binding, this, permissionHandler, pathHandler, wordHandler, logicHandler);


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
            binding.EditRepetition.setOnEditorActionListener(editorHandler);
            binding.EditClue.setOnEditorActionListener(editorHandler);


        // executions
        reloadSettings(dataHandler);
        pathHandler.onIntentResult();
        permissionHandler.getPermission();
        AnimHandler.currentPage = binding.pageHome;
        LogicHandler.isRandom = true;
        LogicHandler.wordRepetition = true;
    }

    private void reloadSettings(DataHandler dataHandler) {
        String[] settings = {"random", "caseRepetition", "countRepetition", "maxRepetition", "clue"};
        for (int i = 0; i < settings.length; i++) {
            String var = dataHandler.load(settings[i]);
            switch (i) {
                case 0:
                    LogicHandler.isRandom = Boolean.parseBoolean(var);
                    binding.SwitchRandomMode.setChecked(Boolean.parseBoolean(var));
                    break;
                case 1:
                    LogicHandler.wordRepetition = Boolean.parseBoolean(var);
                    binding.SwitchRepetition.setChecked(Boolean.parseBoolean(var));
                    break;
                case 2:
                    WordHandler.repetitionAmount = Integer.parseInt(var);
                    binding.EditRepetition.setHint(var);
                    break;
                case 3:
                    LogicHandler.forbidRepetition = Boolean.parseBoolean(var);
                    binding.SwitchRepetitionLimit.setChecked(Boolean.parseBoolean(var));
                    break;
                case 4:
                    LogicHandler.countClue = Integer.parseInt(var);
                    binding.EditClue.setHint(var);
                    break;

            }
        }
    }
}

