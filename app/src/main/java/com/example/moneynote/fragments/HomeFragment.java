package com.example.moneynote.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneynote.R;
import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.databinding.FragmentHomeBinding;
import com.example.moneynote.models.UserDataModel;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;


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

        for (int i=0; i < data.size(); i++) {
            if (data.get(i).getType().equals("Expense")) {
                incomeNum += data.get(i).getAmount();
                binding.income.setText(String.valueOf(incomeNum));
            } else if (data.get(i).getType().equals("Income")) {
                expenseNum += data.get(i).getAmount();
                binding.expense.setText(String.valueOf(expenseNum));
            }
        }

        binding.buttonCalendar.setOnClickListener(v -> calendarFragment());
        binding.buttonGraph.setOnClickListener(v-> graphFragment());
        return binding.getRoot();
    }

    private void graphFragment() {replaceFragment(new GraphFragment());}

    private void calendarFragment() {
        replaceFragment(new CalendarFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
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
}