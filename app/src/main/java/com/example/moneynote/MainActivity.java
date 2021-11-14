package com.example.moneynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moneynote.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonCalendar.setOnClickListener(v -> calendarActivity());

        //        Bottom Navigation Bar
        Menu menu = binding.navBar.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        binding.navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        break;

                    case R.id.calendar:
                        calendarActivity();
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

    private void calendarActivity() {
        Intent i = new Intent(this , CalendarActivity.class);
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