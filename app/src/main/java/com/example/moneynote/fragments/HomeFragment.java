package com.example.moneynote.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.chart.common.dataentry.DataEntry;
import com.example.moneynote.R;
import com.example.moneynote.databinding.FragmentHomeBinding;
//<<<<<<< HEAD
import com.example.moneynote.models.UserDataModel;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
//=======

//
import java.util.ArrayList;
//>>>>>>> b7bc7c2 (graph data)


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private String fileName = "data.json";
    private ArrayList<UserDataModel> data;
    private int incomeNum;
    private int expenseNum;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        setData();
        thisMonthData();

        binding.buttonCalendar.setOnClickListener(v -> {
            MoneyNoteUtils.replaceFragment(getActivity(), R.id.fragment_container, new CalendarFragment());
        });
        binding.buttonGraph.setOnClickListener(v-> {
            MoneyNoteUtils.replaceFragment(getActivity(), R.id.fragment_container, new GraphFragment());
        });

//        binding.buttonCalendar.setOnClickListener(v -> calendarFragment());
        binding.buttonGraph.setOnClickListener(v-> graphFragment());
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
                data = gson.fromJson(tempData, new TypeToken<ArrayList<UserDataModel>>() {
                }.getType());
            } catch (IOException d) {
            }

        }





    }


    private void graphFragment()
    {
//        replaceFragment(new GraphFragment());
    }




    private void thisMonthData() {
        String thisMonth = new SimpleDateFormat("MM월", Locale.getDefault()).format(new Date());
        for (int i=0; i < data.size(); i++) {
            if (data.get(i).getType().equals("Expense") && data.get(i).getDate().contains(thisMonth)) {
                incomeNum += data.get(i).getAmount();
                binding.income.setText("+"+String.valueOf(incomeNum));
            } else if (data.get(i).getType().equals("Income") && data.get(i).getDate().contains(thisMonth)) {
                expenseNum += data.get(i).getAmount();
                binding.expense.setText("-"+String.valueOf(expenseNum));
            }
        }
    }
}