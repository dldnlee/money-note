package com.example.moneynote.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneynote.AddFundActivity;
import com.example.moneynote.adapters.ButtonAdapter;
import com.example.moneynote.databinding.FragmentExpenseBinding;
import com.example.moneynote.models.UserDataModel;
import com.example.moneynote.utils.MoneyNoteUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class ExpenseFragment extends Fragment {
    private FragmentExpenseBinding binding;
    private ButtonAdapter adapter;
    private ArrayList<UserDataModel> array = new ArrayList<>();
    private ArrayList<String> labelList = new ArrayList<>();
    private final String saveFileName = "data.json";
    private String userData;
    private String jsonData;
    private String labels;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExpenseBinding.inflate(inflater, container, false);

//        Initial setup
        setData();
        setAdapter();

        AddFundActivity activity = (AddFundActivity) getActivity();
        String selectedDate = activity.getData();
        binding.editDate.setText(selectedDate);

//        Save and Cancel Button
        binding.cancelButton.setOnClickListener(v -> getActivity().finish());
        binding.saveButton.setOnClickListener(v -> {
            if (binding.editCategory.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "????????? ??????????????????", Toast.LENGTH_SHORT).show();
            } else if (binding.editAmount.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "????????? ??????????????????", Toast.LENGTH_SHORT).show();
            } else {
                addData();
                getActivity().finish();
            }
        });

//        Controlling Category MenuBar
        binding.editCategory.setOnClickListener(v -> showCategory());

        return binding.getRoot();
    }

    private void setAdapter() {
        EditText text = binding.editCategory;
        TextView title = binding.title;
        RecyclerView list = binding.listOfButtons;
        adapter = new ButtonAdapter(labelList, text, title, list);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        binding.listOfButtons.setLayoutManager(layoutManager);
        binding.listOfButtons.setItemAnimator(new DefaultItemAnimator());
        binding.listOfButtons.setAdapter(adapter);
    }

    private void setData(){
        try {
            this.labels = MoneyNoteUtils.readFromAssets(getActivity(), "expenselabels.json");
        } catch (IOException e) {
            Log.i("READ", "Read did not succeed");
        }
        Gson gson = new Gson();

        labelList = gson.fromJson(this.labels, new TypeToken<ArrayList<String>>() {
        }.getType());
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

        dataModelArrayList.sort((d1,d2) ->d1.getDate().compareTo(d2.getDate()));

        this.userData = gson.toJson(dataModelArrayList);

        MoneyNoteUtils.writeFile(getActivity(), saveFileName, userData);
    }

    private void showCategory() {
        binding.title.setVisibility(View.VISIBLE);
        binding.listOfButtons.setVisibility(View.VISIBLE);
    }

}