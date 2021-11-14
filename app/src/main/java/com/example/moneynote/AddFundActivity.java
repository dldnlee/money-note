package com.example.moneynote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.moneynote.databinding.ActivityAddFundBinding;

public class AddFundActivity extends AppCompatActivity {
    private ActivityAddFundBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}