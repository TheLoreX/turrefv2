package com.example.turrefv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turrefv2.action.ButtonHandler;
import com.example.turrefv2.action.SwitchHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.databinding.FragmentWordBinding;

public class FragmentWord extends Fragment {

    public FragmentWordBinding binding;
    public ButtonHandler buttonHandler;
    public TouchHandler touchHandler;
    public SwitchHandler switchHandler;

    public FragmentWord(FragmentWordBinding fragmentWordBinding, ButtonHandler buttonHandler, TouchHandler touchHandler, SwitchHandler switchHandler) {
        super(R.layout.fragment_word);
        this.buttonHandler = buttonHandler;
        this.touchHandler = touchHandler;
        this.switchHandler = switchHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentWordBinding.inflate(inflater, container, false);

        binding.ButtonBack.setOnClickListener(buttonHandler);
        binding.ButtonBack.setOnTouchListener(touchHandler);
        binding.ButtonSlider.setOnClickListener(buttonHandler);
        binding.ButtonSlider.setOnTouchListener(touchHandler);
        binding.ButtonReplay.setOnClickListener(buttonHandler);
        binding.ButtonReplay.setOnTouchListener(touchHandler);
        binding.ButtonClue.setOnClickListener(buttonHandler);
        binding.ButtonClue.setOnTouchListener(touchHandler);
        binding.ButtonUpperDisplay.setOnClickListener(buttonHandler);
        binding.ButtonUpperDisplay.setOnTouchListener(touchHandler);
        binding.ButtonLowerDisplay.setOnClickListener(buttonHandler);
        binding.ButtonLowerDisplay.setOnTouchListener(touchHandler);
        binding.SwitchSide.setOnCheckedChangeListener(switchHandler);
        binding.ButtonReturn.setOnClickListener(buttonHandler);
        binding.ButtonReturn.setOnTouchListener(touchHandler);
        binding.ButtonReset.setOnClickListener(buttonHandler);
        binding.ButtonReset.setOnTouchListener(touchHandler);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}