package com.example.turrefv2;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class WordHandler {

    public static HashMap<Integer, Register> repetitionList = new HashMap<>();
    public static int LineCount, WordCount, Queue, selectedLine, repetitionAmount;
    public static boolean isExist;
    File readFile;

    public void isFileExist() {

        readFile = new File(Environment.getExternalStorageDirectory(), PathHandler.path);
        if(readFile.exists()) isExist = true;
        else isExist = false;
    }

    public void WordCounter() {
        WordCount = 0;
        String readLine;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"));
            while ((readLine = reader.readLine()) != null && readLine.length() > 0) {
                readLine = readLine.replace('=', ' ');
                String[] readWords = readLine.split("\\s+");
                WordCount += readWords.length;
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void LineCounter() {

        LineCount = 0;
        String readLine;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "UTF-8"));
            while ((readLine = reader.readLine()) != null && readLine.length() > 0) {
                LineCount++;
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String ReadManagement() {

        if(LogicHandler.isRandom && LogicHandler.wordRepetition) {
            if(LogicHandler.forbidRepetition)
                selectedLine = RepetitionPreventer(LineCount);
            else
                selectedLine = RepetitionPreventer(repetitionAmount);
        }
        else {
            selectedLine++;
            if(selectedLine >= LineCount) {
                selectedLine = 0;
                LogicHandler.countSpin = 0;
            }
        }

        return LineReader(selectedLine);
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
        if(repetitionList.size() != 0) {
            bounds:
            while(true) {
                selectedLine = rand.nextInt(LineCount);
                for(int i = 0; i < repetitionList.size(); i++) {
                    if(selectedLine == repetitionList.get(i).Num) {
                        break;
                    }
                    else {
                        if(i == repetitionList.size() - 1) {
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
           repetitionList.put(Queue, new Register(selectedLine,0));

            for(int i = 0; i < repetitionList.size(); i++) {
                repetitionList.put(i, new Register(repetitionList.get(i).Num, repetitionList.get(i).Roll + 1));

                if(repetitionList.get(i).Roll >= Repetition) {
                    Queue = i - 1;
                }
            }
        }
        else {
            repetitionList.put(Queue, new Register(selectedLine = rand.nextInt(LineCount),1));
        }

        return selectedLine;
    }

    private class Register {
        int Num, Roll;
        Register(int Num, int Roll) {
            this.Num = Num;
            this.Roll = Roll;
        }
    }

}
