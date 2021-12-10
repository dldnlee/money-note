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

import com.anychart.core.ui.Title;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.moneynote.AddFundActivity;
import com.example.moneynote.R;
import com.example.moneynote.databinding.FragmentGraphBinding;

import com.example.moneynote.models.UserDataModel;

import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


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

        setData();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("지출"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("수입"));

        pie = AnyChart.pie();

        setExpenseGraph();


        Title title = pie.title();

        title
                .enabled(true)
                .text("지출 통계")
                .fontSize(30)
                .fontWeight(600);



        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (binding.tabLayout.getSelectedTabPosition()){
                    case 0:
                        title.text("지출 통계");
                        setExpenseGraph();
                        break;

                    case 1:
                        title.text("수입 통계");
                        setIncomeGraph();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pie.legend()
                .itemsLayout(LegendLayout.VERTICAL)
                .fontSize(15)
                .maxHeight(150)
                .align(Align.CENTER);

        binding.anyChartView.setChart(pie);

        return binding.getRoot();
    }

    private void setData(){
        try {
            Gson gson = new Gson();
            String userData = MoneyNoteUtils.readFile(getActivity(), fileName);
            data = gson.fromJson(userData, new TypeToken<ArrayList<UserDataModel>>(){
            }.getType());
        } catch (IOException e) {

        }
        expenseData();
        incomeData();
    }

    private void expenseData() {

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("식비")) {
                foodTotal += data.get(i).getAmount();
            } else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("패션/미용")) {
                clothesTotal += data.get(i).getAmount();
            }
            else if (data.get(i).getType().equals("Expense") && data.get(i).getCategory().equals("문화생활")) {
                activityTotal += data.get(i).getAmount();
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
    }

    private void setExpenseGraph() {

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
        data.add(new ValueDataEntry("교통", transportationTotal));

        pie.data(data);
        pie.animation(true);
    }

    private void setIncomeGraph() {

        List<DataEntry> data = new ArrayList<>();

        data.add(new ValueDataEntry("용돈", allowanceTotal));
        data.add(new ValueDataEntry("월급", salaryTotal));
        data.add(new ValueDataEntry("장학금", scholarsTotal));
        data.add(new ValueDataEntry("대출", loanTotal));
        data.add(new ValueDataEntry("친구", friendsTotal));
        data.add(new ValueDataEntry("기타", incomeEtcTotal));

        pie.data(data);
        pie.animation(true);
    }

}
