package com.example.turrefv2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;

public class PathHandler extends AppCompatActivity{

    public static void pathReceiver(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        Uri uri = Uri.parse(String.valueOf(Environment.getExternalStorageDirectory()));
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setDataAndType(uri, "text/plain");
        MainActivity.activityResultLauncher.launch(intent);
    }

}
