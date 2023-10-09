package com.example.turrefv2.component;

import android.content.Context;
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

import java.util.ArrayList;

public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.ViewHolder> {

    Context context;
    ActivityMainBinding binding;
    AnimHandler animHandler;
    ArrayList<String> recList;

    public RecentListAdapter(Context context, AnimHandler animHandler, ArrayList<String> recList, ActivityMainBinding binding) {
        this.recList = recList;
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
        holder.Tag.setText(recList.get(position));
        //holder.Tag.setText(recList.get(position).substring(recList.get(position).lastIndexOf("/") + 1, recList.get(position).indexOf(".")));
        holder.Notepad.setOnTouchListener(new TouchHandler(context));
        holder.Notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickListener.onButtonClick(recList.get(holder.getAbsoluteAdapterPosition()));
            }
        });
    }

    private ButtonClickListener buttonClickListener = new ButtonClickListener() {
        @Override
        public void onButtonClick(String path) {
            animHandler.changeFileFromRecList(path);
        }
    };

    @Override
    public int getItemCount() {
        return recList.size();
    }

}
