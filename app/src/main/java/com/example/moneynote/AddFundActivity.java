package com.example.moneynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.moneynote.databinding.ActivityAddFundBinding;
import com.example.moneynote.fragments.ExpenseFragment;
import com.example.moneynote.fragments.IncomeFragment;

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

        replaceFragment(R.id.fragment_container, new ExpenseFragment());

        binding.expenseOption.setOnClickListener(v -> {
            replaceFragment(R.id.fragment_container, new ExpenseFragment());
            binding.title.setText(R.string.add_fund_activity_expense);
        });
        binding.incomeOption.setOnClickListener(v -> {
            replaceFragment(R.id.fragment_container, new IncomeFragment());
            binding.title.setText(R.string.add_fund_activity_income);
        });
    }

    public void replaceFragment(int id, Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit();
    }

    public String getData() {
        return selectedDate;
    }
}