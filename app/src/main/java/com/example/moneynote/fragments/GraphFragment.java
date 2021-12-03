package com.example.moneynote.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;


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

import java.util.ArrayList;
import java.util.List;


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
        data.add(new ValueDataEntry("John", 1000));
        data.add(new ValueDataEntry("John", 1000));
        data.add(new ValueDataEntry("John", 1000));

        pie.data(data);

        anyChartView.setChart(pie);
        return binding.getRoot();
    }



}