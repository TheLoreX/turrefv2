package com.example.turrefv2;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class PathHandler extends  AppCompatActivity{

    Activity activity;

    public PathHandler(Activity activity) {
        this.activity = activity;
    }

    public void pathReceiver() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
        //intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
        //intent.addCategory(Intent.CATEGORY_OPENABLE);
        //intent.setType("text/plain");
        Uri uri = Uri.fromParts("package",getPackageName(),null);
        intent.setData(uri);

        MainActivity.activityResultLauncher.launch(intent);
    }
}
