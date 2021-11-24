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
    private String userData;
    private final String saveFileName = "userdata.json";
    private String test;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExpenseBinding.inflate(inflater, container, false);

        binding.saveButton.setOnClickListener(v -> {
            addData();
            binding.testText.setText(userData);
        });




        return binding.getRoot();
    }


    private void loadData(Context context, String fileName) {
        try {
            this.test = MoneyNoteUtils.readFile(context, fileName);
        } catch (IOException e){

        }
    }

    private void addData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String usrDate = binding.editDate.getText().toString();
        String usrCategory = binding.editCategory.getText().toString();
        int usrAmount = Integer.parseInt(binding.editAmount.getText().toString());
        String usrDescription = binding.editDesc.getText().toString();

        loadData(getActivity(), saveFileName);

        ArrayList<UserDataModel> anotherTest = gson.fromJson(this.test, new TypeToken<ArrayList<UserDataModel>>() {
        }.getType());

        UserDataModel data = new UserDataModel(usrDate, usrCategory, usrAmount, usrDescription);
//        this.userData = gson.toJson(data);

        anotherTest.add(data);

        this.userData = gson.toJson(anotherTest);



//        this.userData = gson.toJson(array);


        MoneyNoteUtils.writeFile(getActivity(), saveFileName, userData);

//        MoneyNoteUtils.appendWriteFile(saveFileName, userData);



        Toast.makeText(getActivity(),"Helo",Toast.LENGTH_SHORT).show();
    }
}