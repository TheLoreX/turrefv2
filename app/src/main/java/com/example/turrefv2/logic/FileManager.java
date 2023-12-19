package com.example.turrefv2.logic;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.turrefv2.MainActivity;
import com.example.turrefv2.utils.SettingsManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManager {

    Context context;
    public static boolean isFileExist, isFileGlobal;
    public static File readFile;
    public static Uri readLink;
    public static String fileName;

    public FileManager(Context context) {
        this.context = context;
    }

    public boolean isFileGlobal() {
        return isFileGlobal = PathHandler.path.contains("com.microsoft.skydrive.content.metadata");
    }

    public void isFileExist(boolean isPresent) {
        if(isFileGlobal()) {
            readLink = Uri.parse(PathHandler.path);
            if(!isPresent) {
                fileNameRetriever();
            }
            isFileExist = true;
        }
        else {
            readFile = new File(PathHandler.path);
            fileName = PathHandler.path.substring(PathHandler.path.lastIndexOf("/") + 1,PathHandler.path.indexOf("."));
            isFileExist = readFile.exists();
        }
    }

    private void fileNameRetriever() {
        if (readLink.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            Cursor cursor = context.getApplicationContext().getContentResolver().query(readLink,
                    new String[]{MediaStore.MediaColumns.DISPLAY_NAME},
                    null,
                    null,
                    null);
            if (cursor != null) {
                cursor.moveToFirst();
                fileName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)).replace(".txt", "");
                cursor.close();
            }
        }
    }

    public void recentDirManager() { // recreates files into Recentdir

        try {
            // defines InputStream based on it's status(global/local)
            InputStream inputStream = FileManager.isFileGlobal ? context.getContentResolver().openInputStream(readLink) : new FileInputStream(readFile.getPath());
            OutputStream outputStream = new FileOutputStream(DataHandler.recentDirPath + File.separator + FileManager.fileName + ".txt");

            int length;  byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close(); outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
