package com.example.turrefv2.logic;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.example.turrefv2.MainActivity;
import com.example.turrefv2.utils.SettingsManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

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
        for (String drive : PathHandler.globalDrives) {
            if(isFileGlobal = PathHandler.path.contains(drive))
                return isFileGlobal = true;
        }
        return isFileGlobal = false;
    }

    public void isFileExist(boolean isNamePresent) {
        if(isFileGlobal()) {
            readLink = Uri.parse(PathHandler.path);
            if(!isNamePresent) {
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

    public void recentDirManager() { // recreates files into "Recentdir"

        try {
            // defines InputStream based on it's status(global/local)
            BufferedReader reader;
            if (isFileGlobal) {
                reader = new BufferedReader(new InputStreamReader(context.getContentResolver().openInputStream(readLink), "UTF-8"));
            }
            else {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile.getPath()), "UTF-8"));
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(DataHandler.recentDirPath + File.separator + FileManager.fileName + ".txt"));

            String readLine;
            while ((readLine = reader.readLine()) != null && readLine.length() > 0) {
                writer.write(readLine);
                writer.newLine();
            }
            reader.close(); writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
