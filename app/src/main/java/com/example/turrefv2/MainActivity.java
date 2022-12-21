package com.example.turrefv2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.turrefv2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public static String path;
    public static ActivityResultLauncher<Intent> activityResultLauncher;
    public static int countOfLine;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // class definitions
        ButtonHandler buttonHandler = new ButtonHandler( binding, this);
        TouchHandler touchHandler = new TouchHandler(this);
        AnimHandler animHandler = new AnimHandler(binding,this);
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

        // executions
        getPermission();


        // ---
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    if (result.getData() != null) {
                        path = "/" + result.getData().getData().getPath().substring(result.getData().getData().getPath().indexOf(":") + 1);
                        animHandler.openAttach();
                        buttonHandler.notepadInform(path);
                    }
                }
            }
        });
    }


    public void getPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1000);
        }
    }
}

