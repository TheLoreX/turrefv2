package com.example.turrefv2.component;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turrefv2.R;
import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.databinding.ActivityMainBinding;
import com.example.turrefv2.logic.DataHandler;
import com.example.turrefv2.logic.FileManager;

import java.io.File;
import java.util.ArrayList;

public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.ViewHolder> {

    Context context;
    ActivityMainBinding binding;
    AnimHandler animHandler;
    ArrayList<String[]> recentList;

    public RecentListAdapter(ActivityMainBinding binding ,Context context, AnimHandler animHandler, ArrayList<String[]> recentList) {
        this.recentList = recentList;
        this.context = context;
        this.binding = binding;
        this.animHandler = animHandler;
    }

    private interface ButtonClickListener {
        void onButtonClick(String position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button Notepad;
        private TextView Tag;
        public ViewHolder(View view) {
            super(view);
            Notepad = view.findViewById(R.id.Notepad);
            Tag = view.findViewById(R.id.Tag);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recentlists_recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Tag.setText(recentList.get(position)[0]);
        FileManager.fileName = recentList.get(position)[0];
        holder.Notepad.setOnTouchListener(new TouchHandler(context));
        holder.Notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickListener.onButtonClick(recentList.get(holder.getAbsoluteAdapterPosition())[0]);
                Log.d("TTST/click", holder.getAbsoluteAdapterPosition() + " " + recentList.get(holder.getAbsoluteAdapterPosition())[0]);
            }
        });
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener() {
        @Override
        public void onButtonClick(String fileName) {
            animHandler.changeFileFromRecList(DataHandler.recentDirPath + File.separator + fileName + ".txt");
            Log.d("TTST/click", " " + DataHandler.recentDirPath + File.separator + fileName + ".txt");
        }
    };

    @Override
    public int getItemCount() {
        return recentList.size();
    }

}
