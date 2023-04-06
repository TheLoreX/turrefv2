package com.example.turrefv2.logic;

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

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.logic.WordHandler;

public class PathHandler {

    AppCompatActivity compatActivity;
    AnimHandler animHandler;
    WordHandler wordHandler;

    public PathHandler(AppCompatActivity compatActivity, AnimHandler animHandler, WordHandler wordHandler) {
        this.compatActivity = compatActivity;
        this.animHandler = animHandler;
        this.wordHandler = wordHandler;
    }

    public ActivityResultLauncher<Intent> activityResultLauncher;
    public static String path;

    public void pathReceiver() {
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
                        animHandler.openAttach();
                        animHandler.setRecentList();
                    }
                }
            }
        });
    }

}
