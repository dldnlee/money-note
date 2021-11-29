package com.example.moneynote.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.moneynote.AddFundActivity;
import com.example.moneynote.MoneyNoteAdapter;
import com.example.moneynote.databinding.FragmentCalendarBinding;
import com.example.moneynote.model.UserDataModel;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class CalendarFragment extends Fragment {
    private FragmentCalendarBinding binding;
    private ArrayList<UserDataModel> data;
    private ArrayList<UserDataModel> filteredList = new ArrayList<UserDataModel>();
    private String fileName = "data.json";
    private MoneyNoteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);

        String date = new SimpleDateFormat("yyyy년MM월dd일", Locale.getDefault()).format(new Date());
        binding.subheading.setText(date);

        binding.addButton.setOnClickListener(v -> addFundActivity());

        setData();
        dataFilter(date);
        setAdapter();

//        Calendar
        binding.calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                filteredList.clear();
                String selectedDate = String.format("%d년%d월%d일", year, (month + 1), dayOfMonth);
                binding.subheading.setText(selectedDate);
                dataFilter(selectedDate);
                adapter.notifyDataSetChanged();
            }
        });
        return binding.getRoot();
    }

    private void addFundActivity() {
        Intent i = new Intent(getActivity(), AddFundActivity.class);
        String selectedDate = binding.subheading.getText().toString();
        i.putExtra("SELECTED_DATE", selectedDate);
        startActivity(i);
    }

    private void setAdapter() {
        adapter = new MoneyNoteAdapter(filteredList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.listOfItems.setLayoutManager(layoutManager);
        binding.listOfItems.setItemAnimator(new DefaultItemAnimator());
        binding.listOfItems.setAdapter(adapter);
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

    private void dataFilter(String selectedDate) {
        for (int i = 0; i<this.data.size(); i++) {
            UserDataModel item = this.data.get(i);
            if (item.getDate().equals(selectedDate)) {
                this.filteredList.add(item);
            } else {
                continue;
            }
        }
    }
}