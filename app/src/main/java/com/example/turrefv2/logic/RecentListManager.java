package com.example.turrefv2.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.turrefv2.component.RecyclerAdapter;
import com.example.turrefv2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class RecentListManager {

    Context context;
    ActivityMainBinding binding;
    SharedPreferences storedRecList;

    public RecentListManager(ActivityMainBinding binding,Context context) {
        this.context = context;
        this.binding = binding;
        storedRecList = context.getSharedPreferences("StoredRecentLists", Context.MODE_PRIVATE);
    }

    private ArrayList<String> recList;

    public void setList()  {
        recList = loadList();

        if (recList.size() > 1) {
            for (int i = recList.size() - 1; i > 0; i--) {
                recList.set(i, recList.get(i - 1));
            }
            storeList();
        }
        else {
            if (recList.size() == 1) {
                //recList.add(recList.get(0));
                //recList.set(0, PathHandler.path);
            }
            storeList();
        }

    }

    private void storeList() {
        recList.add(0,PathHandler.path);
        for (int i = 0; i < recList.size() && i < 4; i++) {
            storedRecList.edit().putString(String.valueOf(i), recList.get(i)).apply();
        }
        Log.d("TTST/StoredRec", storedRecList.getAll().size() + " : " + storedRecList.getAll());
    }

    private ArrayList<String> loadList() {

        ArrayList<String> cloneRecList = new ArrayList<String>();
        for (int i = 0; i < storedRecList.getAll().size() && i < 4; i++) {
            cloneRecList.add(i, storedRecList.getString(String.valueOf(i), null));
        }
        Log.d("TTST/LoadedRec", storedRecList.getAll().size() + " : " + storedRecList.getAll());
        return cloneRecList;
    }

    public void showRecycler() {
        recList = loadList();
        setRecyclerAdapter();
        storedRecList.edit().clear().apply();
    }

    private void setRecyclerAdapter() {
        binding.rtest.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.rtest.setItemAnimator(new DefaultItemAnimator());
        binding.rtest.setAdapter(new RecyclerAdapter(recList.size()));
    }


}
