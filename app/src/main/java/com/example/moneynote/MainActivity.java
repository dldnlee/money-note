package com.example.moneynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.moneynote.databinding.ActivityMainBinding;
import com.example.moneynote.fragments.CalendarFragment;
import com.example.moneynote.fragments.GraphFragment;
import com.example.moneynote.fragments.HomeFragment;
import com.example.moneynote.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        homeFragment();

        binding.navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        homeFragment();
                        break;

                    case R.id.calendar:
                        calendarFragment();
                        break;

                    case R.id.graph:
                        graphFragment();
                        break;

                    case R.id.settings:
                        settingsFragment();
                        break;
                }

                return false;
            }
        });

    }


    private void homeFragment() {
        replaceFragment(new HomeFragment());
    }

    private void graphFragment() {
        replaceFragment(new GraphFragment());
    }

    private void settingsFragment() {
        replaceFragment(new SettingsFragment());
    }

    private void calendarFragment() {
        replaceFragment(new CalendarFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }


}