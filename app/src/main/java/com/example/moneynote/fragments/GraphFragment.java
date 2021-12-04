package com.example.moneynote.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.moneynote.R;
import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.databinding.FragmentGraphBinding;
import com.example.moneynote.models.UserDataModel;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class GraphFragment extends Fragment {
    private FragmentGraphBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGraphBinding.inflate(inflater, container, false);

//        AnyChartView anyChartView = findViewById(R.id.any_chart_view);

        AnyChartView anyChartView = binding.anyChartView;
//                AnyChartView anyChartView = (AnyChartView) findViewById(R.id.any_chart_view);


        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();

        pie.data(data);

        anyChartView.setChart(pie);
        return binding.getRoot();
    }




}