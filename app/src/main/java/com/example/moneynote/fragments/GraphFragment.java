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
    private Pie pie;

    int foodTotal = 0;
    int clothesTotal = 0;
    int transportationTotal = 0;
    int activityTotal = 0;
    int martTotal = 0;
    int suppliesTotal = 0;
    int tripTotal = 0;
    int savingsTotal = 0;
    int giftTotal = 0;
    int parentsTotal = 0;
    int incomeEtcTotal = 0;
    int scholarsTotal = 0;
    int allowanceTotal = 0;
    int salaryTotal = 0;
    int loanTotal = 0;
    int friendsTotal = 0;
    int expenseEtcTotal = 0;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGraphBinding.inflate(inflater, container, false);


        binding.expenseOption.setOnClickListener(v -> expenseData());
        binding.incomeOption.setOnClickListener(v -> incomeData());

        pie = AnyChart.pie();
        AnyChartView anyChartView = binding.anyChartView;
        anyChartView.setChart(pie);

        showExpenseGraph();
        setData();
        expenseData();

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

//        ArrayList<UserDataModel> items = new ArrayList<UserDataModel>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("식비")) {
                foodTotal += data.get(i).getAmount();
            } else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("패션/미용")) {
                clothesTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("문화생활")) {
                clothesTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("교통")) {
                transportationTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("마트/편의점")) {
                martTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("생활용품")) {
                suppliesTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("여행")) {
                tripTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("적금")) {
                savingsTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("선물")) {
                giftTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("부모님")) {
                parentsTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("기타")) {
                expenseEtcTotal += data.get(i).getAmount();
            }


        }
        showExpenseGraph();
    }

    private void incomeData(){

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType().equals("Income") && data.get(i).getCategory().equals("월급")) {
                salaryTotal += data.get(i).getAmount();
            } else if (data.get(i).getType().equals("Income") && data.get(i).getCategory().equals("용돈")) {
                allowanceTotal += data.get(i).getAmount();
                }
            else if (data.get(i).getType().equals("Income") && data.get(i).getCategory().equals("장학금")) {
                scholarsTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Income") && data.get(i).getCategory().equals("대출")) {
                loanTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Income") && data.get(i).getCategory().equals("친구")) {
                friendsTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Income") && data.get(i).getCategory().equals("기타")) {
                incomeEtcTotal += data.get(i).getAmount();
            }

        }
        showIncomeGraph();

    }
    private void showExpenseGraph() {



        List<DataEntry> data = new ArrayList<>();

        data.add(new ValueDataEntry("식비", foodTotal));
        data.add(new ValueDataEntry("패션/미용", clothesTotal));
        data.add(new ValueDataEntry("문화생활", activityTotal));
        data.add(new ValueDataEntry("마트/편의점", martTotal));
        data.add(new ValueDataEntry("생활용품", suppliesTotal));
        data.add(new ValueDataEntry("여행", tripTotal));
        data.add(new ValueDataEntry("적금", savingsTotal));
        data.add(new ValueDataEntry("선물", giftTotal ));
        data.add(new ValueDataEntry("부모님", parentsTotal));
        data.add(new ValueDataEntry("기타", expenseEtcTotal));


        pie.data(data);
        pie.animation(true);

//        anyChartView.setChart(pie);
        }
    private void showIncomeGraph() {



        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("용돈", allowanceTotal));
        data.add(new ValueDataEntry("월급", salaryTotal));
        data.add(new ValueDataEntry("장학금", scholarsTotal));
        data.add(new ValueDataEntry("대출", loanTotal));
        data.add(new ValueDataEntry("친구", friendsTotal));
        data.add(new ValueDataEntry("기타", incomeEtcTotal));

        pie.data(data);
        pie.animation(true);

//        anyChartView.setChart(pie);
    }

}
