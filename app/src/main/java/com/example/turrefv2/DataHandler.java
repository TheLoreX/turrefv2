package com.example.turrefv2;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataHandler {

    Context context;
    public DataHandler(Context context) {
        this.context = context;
    }

    public String load(String setting) {
        String readLine;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(context.getFilesDir() + "/varSettings.txt")));
            while ((readLine = reader.readLine()) != null && readLine.length() > 0) {
                if (readLine.contains(setting)) {
                    return readLine.substring(readLine.indexOf(":")+ 2);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void store(String setting, String id) {
        String readLine, storeLine = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(context.getFilesDir() + "/varSettings.txt"), "UTF-8"));
            while ((readLine = reader.readLine()) != null && readLine.length() > 0) {
                if (readLine.contains(setting)) {
                    storeLine += readLine.substring(0, readLine.indexOf(":") + 2) + id + "\n";
                } else {
                    storeLine += readLine + "\n";
                }
            }
            reader.close();

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(context.getFilesDir() + "/varSettings.txt"), "UTF-8");
            writer.write(storeLine);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* USED TO REWRITE THE SETTINGS
    public void backup() {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(context.getFilesDir() + "/varSettings.txt"), "UTF-8"));
            writer.write("random: true");
            writer.write("\ncaseRepetition: true");
            writer.write("\ncountRepetition: 5");
            writer.write("\nmaxRepetition: false");
            writer.write("\nclue: 1");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */

}