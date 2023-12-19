package com.example.turrefv2.logic;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.action.AnimHandler;

public class PathHandler {

    AppCompatActivity compatActivity;
    AnimHandler animHandler;

    public PathHandler(AppCompatActivity compatActivity, AnimHandler animHandler) {
        this.compatActivity = compatActivity;
        this.animHandler = animHandler;
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
            if(result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                // checks whether the file is on cloud or not
                if (result.getData().getData().getPath().contains("com.microsoft.skydrive.content.metadata")) {
                    path = result.getData().getData().toString();
                }
                else {
                    path = "/" + result.getData().getData().getPath().substring(result.getData().getData().getPath().indexOf(":") + 1);
                }

                animHandler.openAttach();
                animHandler.setRecentList();

            }
        }
    });
    }
}
