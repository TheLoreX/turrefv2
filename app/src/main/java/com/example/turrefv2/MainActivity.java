package com.example.turrefv2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public static String path;
    public static int countOfLine;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // class definitions
        TouchHandler touchHandler = new TouchHandler(this);
        PathHandler pathHandler = new PathHandler(this, this);
        PermissionHandler permissionHandler = new PermissionHandler(this, this);
        ButtonHandler buttonHandler = new ButtonHandler( binding, this, permissionHandler, pathHandler);

        // listeners
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
        binding.ButtonBack.setOnClickListener(buttonHandler);
        binding.ButtonBack.setOnTouchListener(touchHandler);
        // executions
        pathHandler.onIntentResult();
        permissionHandler.getPermission();
    }
}

