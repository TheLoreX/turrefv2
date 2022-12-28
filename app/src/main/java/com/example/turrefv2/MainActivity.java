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
            binding.ButtonClue.setOnClickListener(buttonHandler);
            binding.ButtonClue.setOnTouchListener(touchHandler);
            binding.upperDisplay.setOnClickListener(buttonHandler);
            binding.upperDisplay.setOnTouchListener(touchHandler);
            binding.lowerDisplay.setOnClickListener(buttonHandler);
            binding.lowerDisplay.setOnTouchListener(touchHandler);
        // executions
        pathHandler.onIntentResult();
        permissionHandler.getPermission();
    }
}

