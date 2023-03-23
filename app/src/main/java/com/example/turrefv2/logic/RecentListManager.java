package com.example.turrefv2.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.turrefv2.component.RecyclerAdapter;
import com.example.turrefv2.databinding.ActivityMainBinding;

import java.util.BitSet;

public class RecentListManager {

    Context context;
    ActivityMainBinding binding;
    SharedPreferences storedRecList;

    public RecentListManager(ActivityMainBinding binding,Context context) {
        this.context = context;
        this.binding = binding;
        storedRecList = context.getSharedPreferences("StoredRecentLists", Context.MODE_PRIVATE);
    }

    private String[] recList;

    public void setList()  {
        recList = loadList();
        if (WordHandler.isExist) {
            for (int i = recList.length - 1; i > 0; i--) {
                recList[i] = recList[i - 1];
            }
            storeList();
            setRecyclerAdapter();
        }
    }

    private void storeList() {
        recList[0] = PathHandler.path;
        for (int i = 0; i < recList.length; i++) {
            storedRecList.edit().putString(String.valueOf(i), recList[i]).apply();
        }
    }

    private String[] loadList() {
        String[] cloneRecList = new String[4];
        for (int i = 0; i < storedRecList.getAll().size(); i++) {
            cloneRecList[i] = storedRecList.getString(String.valueOf(i), null);
        }
        return cloneRecList;
    }

    private void setRecyclerAdapter() {
        binding.rtest.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.rtest.setItemAnimator(new DefaultItemAnimator());
        binding.rtest.setAdapter(new RecyclerAdapter());
    }


}
