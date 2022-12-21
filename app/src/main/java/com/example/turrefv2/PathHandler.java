package com.example.turrefv2;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

public class PathHandler {

    public static void pathReceiver() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(uri, "text/plain");
        MainActivity.activityResultLauncher.launch(intent);
    }
}
