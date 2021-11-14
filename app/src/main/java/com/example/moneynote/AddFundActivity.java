package com.example.moneynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.moneynote.databinding.ActivityAddFundBinding;

public class AddFundActivity extends AppCompatActivity {
    private ActivityAddFundBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.expenseOption.setOnClickListener(v -> {
            replaceFragment(new ExpenseFragment());
            binding.title.setText(R.string.add_fund_activity_expense);
        });

        binding.incomeOption.setOnClickListener(v -> {
            replaceFragment(new IncomeFragment());
            binding.title.setText(R.string.add_fund_activity_income);
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }
}