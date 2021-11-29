package com.example.moneynote.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneynote.AddFundActivity;
import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.databinding.FragmentHomeBinding;
import com.example.moneynote.model.UserDataModel;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ExpenseFragment extends Fragment {
    private FragmentExpenseBinding binding;
    private ArrayList<UserDataModel> array = new ArrayList<>();
    private final String saveFileName = "data.json";
    private String userData;
    private String jsonData;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExpenseBinding.inflate(inflater, container, false);

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
            Log.i("FILEREAD", "Could not find file " + fileName);
        }
    }

    private void addData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String type = "Expense";
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