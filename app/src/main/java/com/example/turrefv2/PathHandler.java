package com.example.turrefv2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class PathHandler {

    public static ActivityResultLauncher<Intent> activityResultLauncher;
    public static String path;
    AppCompatActivity compatActivity;
    ButtonHandler buttonHandler;
    Activity activity;

    public PathHandler(Activity activity,AppCompatActivity compatActivity) {
        this.activity = activity;
        this.compatActivity = compatActivity;
    }

    public void pathReceiver(ButtonHandler buttonHandler) {
        this.buttonHandler = buttonHandler;
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(Uri.parse(Environment.getExternalStorageDirectory().getPath()), "text/plain");
        activityResultLauncher.launch(intent);
    }


    public void onIntentResult() {
        activityResultLauncher = compatActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    if (result.getData() != null) {
                        path = "/" + result.getData().getData().getPath().substring(result.getData().getData().getPath().indexOf(":") + 1);
                        buttonHandler.attachTrigger();
                    }
                }
            }
        });
    }

}
