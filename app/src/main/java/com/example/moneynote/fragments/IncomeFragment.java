package com.example.moneynote.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneynote.AddFundActivity;
import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.databinding.FragmentHomeBinding;
import com.example.moneynote.databinding.FragmentIncomeBinding;
import com.example.moneynote.model.UserDataModel;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;


public class IncomeFragment extends Fragment {
    private FragmentIncomeBinding binding;
    private ArrayList<UserDataModel> array = new ArrayList<>();
    private final String saveFileName = "userdata.json";
    private String userData;
    private String jsonData;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentIncomeBinding.inflate(inflater, container, false);

        AddFundActivity activity = (AddFundActivity) getActivity();
        String selectedDate = activity.getData();

        binding.editDate.setText(selectedDate);

        binding.saveButton.setOnClickListener(v -> {
            addData();
            getActivity().finish();
        });

        return binding.getRoot();
    }

    private void loadData(Context context, String fileName) {
        try {
            this.jsonData = MoneyNoteUtils.readFile(context, fileName);
        } catch (IOException e){

        }
    }

    private void addData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String type = "Income";
        String usrDate = binding.editDate.getText().toString();
        String usrCategory = binding.editCategory.getText().toString();
        int usrAmount = Integer.parseInt(binding.editAmount.getText().toString());
        String usrDescription = binding.editDesc.getText().toString();

        loadData(getActivity(), saveFileName);

        ArrayList<UserDataModel> dataModelArrayList = gson.fromJson(this.jsonData, new TypeToken<ArrayList<UserDataModel>>() {
        }.getType());

        UserDataModel data = new UserDataModel(type, usrDate, usrCategory, usrAmount, usrDescription);

        dataModelArrayList.add(data);

        this.userData = gson.toJson(dataModelArrayList);

        MoneyNoteUtils.writeFile(getActivity(), saveFileName, userData);
    }

}