package com.example.moneynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.moneynote.databinding.ActivityMainBinding;
import com.example.moneynote.fragments.CalendarFragment;
import com.example.moneynote.fragments.GraphFragment;
import com.example.moneynote.fragments.HomeFragment;
import com.example.moneynote.fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    Notification notification = null;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.white);

        homeFragment();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        String chID = getString(R.string.app_name);

        //안드러이드 버전별로 경우가 다르다고 해서 구분
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {     //API「26」이상


            NotificationChannel notificationChannel = new NotificationChannel(chID, chID, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(chID);

            notificationManager.createNotificationChannel(notificationChannel);

            notification = new Notification.Builder(this, chID)
                    .setContentTitle(getString(R.string.app_name))  //title
                    .setContentText("money note를 기록해요!")        //내용
                    .setSmallIcon(R.drawable.icon)                  //아이콘
                    .build();
        } else {
            //API「25」이하

            notification = new Notification.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("money note를 기록해요!")
                    .setSmallIcon(R.drawable.icon)
                    .build();
        }

        notificationManager.notify(1, notification);

        binding.navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        binding.title.setText("홈");
                        homeFragment();
                        break;

                    case R.id.calendar:
                        binding.title.setText("달력");
                        calendarFragment();
                        break;

                    case R.id.graph:
                        binding.title.setText("통계");
                        graphFragment();
                        break;

                    case R.id.settings:
                        binding.title.setText("설정");
                        settingsFragment();
                        break;
                }
                return true;
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

    public void goWhite(View v) {
        view.setBackgroundResource(R.color.white);
    }
    public void goGrey(View v) {
        view.setBackgroundResource(R.color.grey);
    }


}