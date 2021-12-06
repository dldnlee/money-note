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
    private ArrayList<UserDataModel> data;
    private String fileName = "data.json";
    int foodTotal = 0;
    int clothesTotal = 0;
    int allowanceTotal = 0;
    int salaryTotal = 0;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGraphBinding.inflate(inflater, container, false);

        showExpenseGraph();
        setData();
        expenseData();
        binding.expenseOption.setOnClickListener(v -> expenseData());
        binding.incomeOption.setOnClickListener(v -> incomeData());

        return binding.getRoot();



    }

    private void setData(){
        try {
            Gson gson = new Gson();
            String userData = MoneyNoteUtils.readFile(getActivity(), fileName);
            data = gson.fromJson(userData, new TypeToken<ArrayList<UserDataModel>>(){
            }.getType());
        } catch (IOException e) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Log.i("FILEREAD", "Could not find file, created file " + fileName);
            ArrayList<UserDataModel> initialData = new ArrayList<>();
            UserDataModel initialItem = new UserDataModel("", "", "", 0, "");
            String temporary;
            initialData.add(initialItem);
            temporary = gson.toJson(initialData);
            MoneyNoteUtils.writeFile(getActivity(), fileName, temporary);
            try {
                String tempData = MoneyNoteUtils.readFile(getActivity(), fileName);
                data = gson.fromJson(tempData, new TypeToken<ArrayList<UserDataModel>>(){
                }.getType());
            } catch (IOException d) {

            }
        }
    }
    private void expenseData() {
        binding.title.setText("지출");
//        ArrayList<UserDataModel> items = new ArrayList<UserDataModel>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("Food")) {
                foodTotal += data.get(i).getAmount();
            } else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("Clothes")) {
                clothesTotal += data.get(i).getAmount();

            }

        }
        showExpenseGraph();
    }

    private void incomeData(){
    binding.title.setText("지출");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("Salary")) {
                salaryTotal += data.get(i).getAmount();
            } else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("Allowance")) {
                allowanceTotal += data.get(i).getAmount();

                }
        }
        showIncomeGraph();

    }
    private void showExpenseGraph() {

        AnyChartView anyChartView = binding.anyChartView;


        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();

        data.add(new ValueDataEntry("Food", foodTotal));
        data.add(new ValueDataEntry("Clothes", clothesTotal));


        pie.data(data);

        anyChartView.setChart(pie);
        }
    private void showIncomeGraph() {

        AnyChartView anyChartView = binding.anyChartView;


        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Allowance", allowanceTotal));
        data.add(new ValueDataEntry("Salary", salaryTotal));

        pie.data(data);
        pie.animation(true);

        anyChartView.setChart(pie);
    }

}
