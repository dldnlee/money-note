package com.example.moneynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.example.moneynote.databinding.ActivityCalendarBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private ActivityCalendarBinding binding;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String date = new SimpleDateFormat("yyyy년MM월dd일", Locale.getDefault()).format(new Date());
        binding.subheading.setText(date);

//        Calendar
        binding.calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String selectedDate = String.format("%d년 %d월 %d일", year, (month + 1), dayOfMonth);
                binding.subheading.setText(selectedDate);
            }
        });

//        Add Button
        binding.addButton.setOnClickListener(v -> {
            addFundActivity();
        });

//        Bottom Navigation Bar
        Menu menu = binding.navBar.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        binding.navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        homeActivity();

                    case R.id.calendar:
                        break;

                    case R.id.graph:
                        graphActivity();
                        break;

                    case R.id.settings:
                        settingsActivity();
                        break;
                }

                return false;
            }
        });

    }

    private void addFundActivity() {
        Intent i = new Intent(this , AddFundActivity.class);
        startActivity(i);
    }

    private void homeActivity() {
        Intent i = new Intent(this , MainActivity.class);
        startActivity(i);
    }

    private void graphActivity() {
        Intent i = new Intent(this , CalendarActivity.class);
        startActivity(i);

    }

    private void settingsActivity() {
        Intent i = new Intent(this , CalendarActivity.class);
        startActivity(i);
    }
}