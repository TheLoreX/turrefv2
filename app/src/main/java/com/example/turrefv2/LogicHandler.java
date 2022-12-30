package com.example.turrefv2;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class LogicHandler {

    public static HashMap<Integer, Reg> Registry = new HashMap<>();
    public static boolean isExist;
    public static int LineCount, Queue;
    File readFile;

    public void isFileExist() {
        readFile = new File(Environment.getExternalStorageDirectory(), PathHandler.path);
        if(readFile.exists()) isExist = true;
        else isExist = false;
    }

    public void LineCounter() {
        try {
        LineCount = 0;
        String readLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"));
        while ((readLine = reader.readLine()) != null && readLine.length() > 0) {
            LineCount++;
        }
        reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadManagement() {
        int selectedLine;

        selectedLine = RepetitionPreventer(5);
        String Words = LineReader(selectedLine);

        return Words;
    }

    public String LineReader(int chosenLine) {

        String readLine = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"));

            for (int i = 0; (readLine = reader.readLine()) != null; i++) {
                if (i == chosenLine) {
                    break;
                }
            }
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return readLine;
    }

    public int RepetitionPreventer(int Repetition) {

        Random rand = new Random();
        int selectedLine;
        if(Registry.size() != 0) {
            bounds:
            while(true) {
                selectedLine = rand.nextInt(LineCount);
                for(int i = 0; i < Registry.size(); i++) {
                    if(selectedLine == Registry.get(i).Num) {
                        break;
                    }
                    else {
                        if(i == Registry.size() - 1) {
                            if(Queue == Repetition - 1) {
                                Queue = 0;
                            }
                            else {
                                Queue++;
                            }
                            break bounds;
                        }
                    }
                }
            }
           Registry.put(Queue, new Reg(selectedLine,0));

            for(int i = 0; i < Registry.size(); i++) {
                Registry.put(i, new Reg(Registry.get(i).Num,Registry.get(i).Roll + 1));

                if(Registry.get(i).Roll >= Repetition) {
                    Queue = i - 1;
                }
            }
        }
        else {
            Registry.put(Queue, new Reg(selectedLine = rand.nextInt(LineCount),1));
        }

        return selectedLine;
    }

    public class Reg {
        int Num, Roll;
        Reg(int Num, int Roll) {
            this.Num = Num;
            this.Roll = Roll;
        }
    }

}
