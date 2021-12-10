package com.example.moneynote.fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneynote.R;
import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.databinding.FragmentHomeBinding;
import com.example.moneynote.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment


        return binding.getRoot();


    }


    }
