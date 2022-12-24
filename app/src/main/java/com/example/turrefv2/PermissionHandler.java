package com.example.turrefv2;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.EventLog;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class PermissionHandler {

    AppCompatActivity compatActivity;
    Activity activity;

    PermissionHandler(AppCompatActivity compatActivity, Activity activity) {
        this.compatActivity = compatActivity;
        this.activity = activity;
    }

    public void getPermission()  {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && compatActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            compatActivity.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && compatActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            compatActivity.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && compatActivity.checkSelfPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            compatActivity.requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE}, 1000);
        }
    }

    public void getBroadPermission(PathHandler pathHandler) {

        new AlertDialog.Builder(activity, android.R.style.Theme_DeviceDefault_Dialog_Alert).setTitle("Permission Manager")
            .setTitle("All Files Permissions")
            .setMessage("You have to enable the file access to be able to continue")
            .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                    intent.setData(uri);
                    pathHandler.activityResultLauncher.launch(intent);
                }
            })
            .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    activity.finishAndRemoveTask();
                }
            }).show();
    }
}
