package com.example.turrefv2.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.component.RecentListAdapter;
import com.example.turrefv2.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class RecentListManager {

    Context context;
    ActivityMainBinding binding;
    AnimHandler animHandler;
    SharedPreferences storedRecList;
    HashMap<String, String> testMap = new HashMap<>();

    public RecentListManager(ActivityMainBinding binding, Context context, AnimHandler animHandler) {
        this.context = context;
        this.binding = binding;
        this.animHandler = animHandler;
        storedRecList = context.getSharedPreferences("StoredRecentLists", Context.MODE_PRIVATE);
    }

    public void initiateRecycler() {
        recList = loadList();
        setRecyclerAdapter();
        //storedRecList.edit().clear().apply();
    }

    private ArrayList<String> recList;

    public void setList()  {
        Log.d("TTST/SharedFiles", loadList().toString());
        recList = loadList();
        storeList();
        setRecyclerAdapter();

    }

    private void storeList() {

        if (!recList.contains(PathHandler.path)) {
            if (recList.size() > 3) {
                recList.remove(3);
            }
            recList.add(0, PathHandler.path);
        }

        for (int i = 0; i < recList.size() && i < 4; i++) {
            storedRecList.edit().putString(String.valueOf(i), recList.get(i)).apply();
        }
    }

    private ArrayList<String> loadList() {

        ArrayList<String> cloneRecList = new ArrayList<String>();
        for (int i = 0; i < storedRecList.getAll().size() && i < 4; i++) {
            cloneRecList.add(i, storedRecList.getString(String.valueOf(i), null));
        }
        return cloneRecList;
    }

    private void setRecyclerAdapter() {
        binding.RecyclerViewRecentList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.RecyclerViewRecentList.setItemAnimator(new DefaultItemAnimator());
        binding.RecyclerViewRecentList.setAdapter(new RecentListAdapter(context, animHandler, recList, binding));
    }

}
