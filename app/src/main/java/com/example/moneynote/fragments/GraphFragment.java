package com.example.moneynote.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.databinding.FragmentGraphBinding;
import com.example.moneynote.databinding.FragmentHomeBinding;


public class GraphFragment extends Fragment {
    private FragmentGraphBinding binding;
//    PieChart pieChart;
//    EditText Product;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGraphBinding.inflate(inflater, container, false);
        return binding.getRoot();

//        pieChart = findViewById(R.id.piechart);
//        setData();
    }

//    private void setData() {
//        pieChart.startAnimation();
//    }
}