package com.example.turrefv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turrefv2.action.AnimHandler;
import com.example.turrefv2.action.ButtonHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.databinding.FragmentHomeBinding;

public class FragmentHome extends Fragment {

    public FragmentHomeBinding binding;
    private ButtonHandler buttonHandler;
    private TouchHandler touchHandler;

    public FragmentHome(FragmentHomeBinding fragmentHomeBinding, ButtonHandler buttonHandler, TouchHandler touchHandler ) {
        super(R.layout.fragment_home);
        this.binding = fragmentHomeBinding;
        this.buttonHandler = buttonHandler;
        this.touchHandler = touchHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AnimHandler.currentPage = binding.pageHome; // sets the currentPage for animation purposes
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.ButtonAdd.setOnClickListener(buttonHandler);
        binding.ButtonAdd.setOnTouchListener(touchHandler);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}