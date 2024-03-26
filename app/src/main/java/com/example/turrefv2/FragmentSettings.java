package com.example.turrefv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.turrefv2.action.ButtonHandler;
import com.example.turrefv2.action.SwitchHandler;
import com.example.turrefv2.action.TouchHandler;
import com.example.turrefv2.component.EditorWrapper;
import com.example.turrefv2.databinding.FragmentSettingsBinding;

public class FragmentSettings extends Fragment {

    public FragmentSettingsBinding binding;
    private ButtonHandler buttonHandler;
    private TouchHandler touchHandler;
    private SwitchHandler switchHandler;
    private EditorWrapper editorWrapper;

    public FragmentSettings(FragmentSettingsBinding fragmentSettingsBinding, ButtonHandler buttonHandler, TouchHandler touchHandler, SwitchHandler switchHandler, EditorWrapper editorWrapper){
        super(R.layout.fragment_word);
        this.buttonHandler = buttonHandler;
        this.touchHandler = touchHandler;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);

        binding.SwitchRandomMode.setOnCheckedChangeListener(switchHandler);
        binding.SwitchRepetition.setOnCheckedChangeListener(switchHandler);
        binding.SwitchRepetitionLimit.setOnCheckedChangeListener(switchHandler);
        binding.ButtonInfoRandomMode.setOnClickListener(buttonHandler);
        binding.ButtonInfoRandomMode.setOnTouchListener(touchHandler);
        binding.ButtonInfoRepetition.setOnClickListener(buttonHandler);
        binding.ButtonInfoRepetition.setOnTouchListener(touchHandler);
        binding.ButtonInfoClue.setOnClickListener(buttonHandler);
        binding.ButtonInfoClue.setOnTouchListener(touchHandler);
        binding.EditRepetition.setOnEditorActionListener(editorWrapper);
        binding.EditClue.setOnEditorActionListener(editorWrapper);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}