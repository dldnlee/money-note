package com.example.moneynote.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.moneynote.AddFundActivity;
import com.example.moneynote.databinding.FragmentCalendarBinding;
import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.databinding.FragmentHomeBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CalendarFragment extends Fragment {
    private FragmentCalendarBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);

        String date = new SimpleDateFormat("yyyy년MM월dd일", Locale.getDefault()).format(new Date());
        binding.subheading.setText(date);

        binding.addButton.setOnClickListener(v -> {
            addFundActivity();
        });

//        Calendar
        binding.calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String selectedDate = String.format("%d년 %d월 %d일", year, (month + 1), dayOfMonth);
                binding.subheading.setText(selectedDate);
            }
        });


        return binding.getRoot();
    }

    private void addFundActivity() {
        Intent i = new Intent(getActivity(), AddFundActivity.class);
        startActivity(i);
    }

}