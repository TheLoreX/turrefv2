package com.example.turrefv2.logic;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.example.turrefv2.MainActivity;
import com.example.turrefv2.utils.SettingsManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataHandler {

    private File mainDir, recentListDir;
    public static String mainDirPath, recentDirPath;

    private boolean checkAuthorization(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && Environment.isExternalStorageManager()) {
            return Environment.isExternalStorageManager();
        }
        else {
            return true;
        }
    }

    public void createMainDir(){
        mainDir = new File(mainDirPath = Environment.getExternalStorageDirectory() + File.separator + MainActivity.appName);
        if (checkAuthorization() && !mainDir.exists()) {
            SettingsManager.storeSettings("mainDirPath", mainDirPath);
            mainDir.mkdirs();
        }
    }

    public void createRecentListDir(){
        recentListDir = new File( recentDirPath = mainDirPath + File.separator + "RecentList");
        SettingsManager.storeSettings("recentDirPath", recentDirPath);
        if (mainDir.exists() && !recentListDir.exists()) {
            recentListDir.mkdirs();
        }
    }

    // creates a registry file of Recentlist
    public void createRecentListRegistry(File recentListRegistry){
        if (!recentListRegistry.exists()) {
            try {
                recentListRegistry.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
