package com.example.moneynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.moneynote.databinding.ActivityAddFundBinding;
import com.example.moneynote.fragments.ExpenseFragment;
import com.example.moneynote.fragments.IncomeFragment;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.android.material.tabs.TabLayout;

public class AddFundActivity extends AppCompatActivity {
    private ActivityAddFundBinding binding;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.selectedDate = extras.getString("SELECTED_DATE");
        }

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("지출"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("수입"));

        MoneyNoteUtils.replaceFragment(this, R.id.fragment_container, new ExpenseFragment());

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (binding.tabLayout.getSelectedTabPosition()){
                    case 0:
                        binding.title.setText("지출 추가");
                        MoneyNoteUtils.replaceFragment(AddFundActivity.this, R.id.fragment_container, new ExpenseFragment());
                        break;

                    case 1:
                        binding.title.setText("수입 추가");
                        MoneyNoteUtils.replaceFragment(AddFundActivity.this, R.id.fragment_container, new IncomeFragment());
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
    }



    public String getData() {
        return selectedDate;
    }
}