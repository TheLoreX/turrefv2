package com.example.turrefv2.logic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.component.RecentListAdapter;
import com.example.turrefv2.databinding.ActivityMainBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;

public class RecentListManager {

    ActivityMainBinding binding;
    Context context;
    AnimHandler animHandler;
    DataHandler dataHandler;

    private static ArrayList<String[]> recentList = new ArrayList<>();
    private File recentListRegistry;

    public RecentListManager(ActivityMainBinding binding, Context context, AnimHandler animHandler, DataHandler dataHandler) {
        this.context = context;
        this.binding = binding;
        this.animHandler = animHandler;
        this.dataHandler = dataHandler;
        recentListRegistry = new File(context.getFilesDir(), "recentlist.dat");
    }

    public void initiateRecycler() {
        dataHandler.createRecentListRegistry(recentListRegistry);
        loadList();
        setRecyclerAdapter();
    }

    public void setList()  {
        storeList();
        setRecyclerAdapter();
    }

    private void storeList() {

        if (!isPathMatching()) {
            if (recentList.size() > 3) {
                new File(DataHandler.recentDirPath + File.separator + recentList.get(3)[0] + ".txt").delete();
                recentList.remove(3);
            }
            recentList.add(0, new String[]{FileManager.fileName, PathHandler.path});

            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(recentListRegistry));
                out.writeObject(recentList);
                out.flush();
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void loadList() {

        try {
            if(recentListRegistry.exists() && recentListRegistry.length() != 0) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(recentListRegistry)); // Reads Arraylist from the saved Registry file
                recentList = (ArrayList<String[]>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    // checks if a file being added, which already exists within the "RecentListRegistry"
    private boolean isPathMatching () {
        for (String[] temp : recentList) {
            if (temp[1].equals(PathHandler.path)) { // "temp[1]" means the path element of the ArrayList "RecentListRegistry""
               return true;
            }
        }
        return false;
    }

    private void setRecyclerAdapter() {
        binding.RecyclerViewRecentList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.RecyclerViewRecentList.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerViewRecentList.setAdapter(new RecentListAdapter(binding, context, animHandler, recentList));
    }
}
