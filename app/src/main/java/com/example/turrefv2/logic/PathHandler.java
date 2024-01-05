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

import java.io.File;
import java.util.ArrayList;

public class PathHandler {

    AppCompatActivity compatActivity;
    AnimHandler animHandler;

    public PathHandler(AppCompatActivity compatActivity, AnimHandler animHandler) {
        this.compatActivity = compatActivity;
        this.animHandler = animHandler;
    }

    public ActivityResultLauncher<Intent> activityResultLauncher;
    public static String path;
    public static final String[] globalDrives = {"com.microsoft.skydrive.content.metadata", "com.google.android.apps.docs.storage"};

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

                if (isPathOnCloud(result)) {
                    path = result.getData().getData().toString();
                }
                else {
                    path = Environment.getExternalStorageDirectory() + File.separator + result.getData().getData().getPath().substring(result.getData().getData().getPath().indexOf(":") + 1);
                }

                animHandler.openAttach(false);
                animHandler.setRecentList();

            }
        }
    });
    }

    // checks whether the file is on cloud or not
    private boolean isPathOnCloud(ActivityResult result) {
        for(String drives : PathHandler.globalDrives) {
            if(result.getData().getData().toString().contains(drives))
                return true;
        }
        return false;
    }

}
