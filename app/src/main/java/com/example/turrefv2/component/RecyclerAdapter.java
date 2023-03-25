package com.example.turrefv2.component;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.turrefv2.R;
import com.example.turrefv2.logic.RecentListManager;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    int itemCount;
    public RecyclerAdapter(int itemCount) {
        this.itemCount = itemCount;
    }

    private ButtonClickListener buttonClickListener;
    public interface ButtonClickListener {

        void onButtonClick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public Button Notepad;
        public ViewHolder(View view) {
            super(view);
            Notepad = view.findViewById(R.id.Notepad);
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
        holder.Notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClickListener.onButtonClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }


    public static class MyButtonClickListenerImpl implements ButtonClickListener {


        @Override
        public void onButtonClick(int pos) {
            Log.d("TTST/OnButtonClick", String.valueOf(pos));
        }
    }
}
