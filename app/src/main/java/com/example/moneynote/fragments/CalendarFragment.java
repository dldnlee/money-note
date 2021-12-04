package com.example.moneynote.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.moneynote.adapters.MoneyNoteAdapter;
import com.example.moneynote.databinding.FragmentCalendarBinding;
import com.example.moneynote.models.UserDataModel;
import com.example.moneynote.utils.EventDecorator;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CalendarFragment extends Fragment implements OnDateSelectedListener {
    private FragmentCalendarBinding binding;
    private ArrayList<UserDataModel> data;
    private ArrayList<UserDataModel> filteredList = new ArrayList<UserDataModel>();
    private String fileName = "data.json";
    private MoneyNoteAdapter adapter;
    private ArrayList<CalendarDay> dates = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);

        String date = new SimpleDateFormat("yyyy년MM월dd일", Locale.getDefault()).format(new Date());
        binding.subheading.setText(date);

        binding.addButton.setOnClickListener(v -> addFundActivity());

        binding.calendar.setOnDateChangedListener(this);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
//        Log.i("ONRESUME", "RESUME CALLED");
        super.onResume();

        filteredList.clear();
        String date = binding.subheading.getText().toString();
        setData();
        dataFilter(date);
        setAdapter();
        setCalendarEvents();
        binding.calendar.addDecorator(new EventDecorator(Color.BLUE, dates));
    }

    @Override
    public void onPause() {
//        Log.i("ONPAUSE", "PAUSE CALLED");
        super.onPause();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonData = gson.toJson(data);
        MoneyNoteUtils.writeFile(getActivity(), fileName, jsonData);
    }

    private void addFundActivity() {
        Intent i = new Intent(getActivity(), AddFundActivity.class);
        String selectedDate = binding.subheading.getText().toString();
        i.putExtra("SELECTED_DATE", selectedDate);
        startActivity(i);
    }

    private void setAdapter() {
        adapter = new MoneyNoteAdapter(getActivity(), filteredList, data);
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

    private void setCalendarEvents() {
        try {
            for(int i=0; i<data.size(); i++) {
                if (!data.get(i).getDate().equals("")) {
                    String dateData = data.get(i).getDate();
                    Date date = new SimpleDateFormat("yyyy년MM월dd일").parse(dateData);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    CalendarDay datesWithTransaction = CalendarDay.from(year, month, day);
                    this.dates.add(datesWithTransaction);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        int year = date.getYear();
        int month = date.getMonth();
        int dayOfMonth = date.getDay();

        filteredList.clear();
        String sDayOfMonth, sMonth;
        if (dayOfMonth < 10 && (month+1) < 10) {
            sDayOfMonth = "0"+String.valueOf(dayOfMonth);
            sMonth = "0"+String.valueOf(month);
        } else if ((month) < 10) {
            sDayOfMonth = String.valueOf(dayOfMonth);
            sMonth = "0"+String.valueOf(month);
        } else if (dayOfMonth < 10){
            sDayOfMonth = "0"+String.valueOf(dayOfMonth);
            sMonth = String.valueOf(month);
        } else {
            sDayOfMonth = String.valueOf(dayOfMonth);
            sMonth = String.valueOf(month);
        }
        String selectedDate = String.format("%d년%s월%s일", year, sMonth, sDayOfMonth);
        binding.subheading.setText(selectedDate);
        dataFilter(selectedDate);
        adapter.notifyDataSetChanged();

    }
}