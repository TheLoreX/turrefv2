package com.example.turrefv2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.turrefv2.action.ActionProvider;
import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.action.ButtonHandler;
import com.example.turrefv2.action.SwitchHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.component.EditorWrapper;
import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.databinding.FragmentHomeBinding;
import com.example.turrefv2.databinding.FragmentSettingsBinding;
import com.example.turrefv2.databinding.FragmentWordBinding;
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
    FragmentHomeBinding fragmentHomeBinding;
    FragmentSettingsBinding fragmentSettingsBinding;
    FragmentWordBinding fragmentWordBinding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // class definitions
        ActionProvider actionProvider = new ActionProvider();
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


        // fragments
        FragmentWord fragmentWord = new FragmentWord(fragmentWordBinding, buttonHandler, touchHandler, switchHandler);
        FragmentHome fragmentHome = new FragmentHome(fragmentHomeBinding, buttonHandler, touchHandler);
        FragmentSettings fragmentSettings = new FragmentSettings(fragmentSettingsBinding, buttonHandler, touchHandler, switchHandler, editorWrapper);
        // listeners
            // palette
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


        // executions
        settingsManager.loadSettings();
        pathHandler.onIntentResult();
        permissionHandler.getPermission();
        recentListManager.initiateRecycler();

        // initiate fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true).add(R.id.pageHome, fragmentWord, null).commit();

    }
}

