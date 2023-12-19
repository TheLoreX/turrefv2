package com.example.turrefv2.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turrefv2.R;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.logic.PathHandler;

public class PermissionHandler {

    AppCompatActivity compatActivity;
    Activity activity;

    public PermissionHandler(AppCompatActivity compatActivity, Activity activity) {
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

        TouchHandler touchHandler = new TouchHandler(activity);

        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.custom_dialog);
        TextView textView = dialog.findViewById(R.id.TextDetails);
        textView.setTextSize(22f);
        textView.setText("Allow APP to access your word lists to be able to continue?");
        dialog.findViewById(R.id.ButtonPositive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                pathHandler.activityResultLauncher.launch(intent);
                dialog.cancel();
            }
        });

        dialog.findViewById(R.id.ButtonPositive).setOnTouchListener(touchHandler);
        dialog.findViewById(R.id.ButtonNegative).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finishAndRemoveTask();
            }
        });
        dialog.findViewById(R.id.ButtonNegative).setOnTouchListener(touchHandler);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}
