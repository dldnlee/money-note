package com.example.moneynote.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneynote.R;
import com.example.moneynote.models.UserDataModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private ArrayList<UserDataModel> array;

    public HomeAdapter(ArrayList<UserDataModel> array) {
        this.array = array;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryText, amountText, dateText;
        private View line, indicator, line2;

        public ViewHolder(final View view){
            super(view);
            categoryText = view.findViewById(R.id.category);
            amountText = view.findViewById(R.id.amount);
            dateText = view.findViewById(R.id.date);
            line = view.findViewById(R.id.first_line);
            indicator = view.findViewById(R.id.indicator);
            line2 = view.findViewById(R.id.second_line);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_fragment_container, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

//        First file contains empty strings so the object with empty strings is hidden
        if(array.get(position).getDate().equals("")) {
            holder.amountText.setVisibility(View.GONE);
            holder.categoryText.setVisibility(View.GONE);
            holder.dateText.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
            holder.indicator.setVisibility(View.GONE);
            holder.line2.setVisibility(View.GONE);

//        First item of the recycler view does not have the first line so style must be different
        } else if (array.get(position).equals(array.get(1))) {
            holder.line.setVisibility(View.GONE);
            String category = array.get(position).getCategory();
            int amount = array.get(position).getAmount();
            String date = array.get(position).getDate();

            if (array.get(position).getType().equals("Expense")) {
                holder.indicator.setBackgroundResource(R.drawable.indicator_expense);
                holder.amountText.setText(String.format("-%d원",amount));
            } else if (array.get(position).getType().equals("Income")) {
                holder.indicator.setBackgroundResource(R.drawable.indicator_income);
                holder.amountText.setText(String.format("+%d원",amount));
            }

            holder.categoryText.setText(category);
            holder.dateText.setText(date);
//        Format for every other item
        } else {
            String category = array.get(position).getCategory();
            int amount = array.get(position).getAmount();
            String date = array.get(position).getDate();

            if (array.get(position).getType().equals("Expense")) {
                holder.indicator.setBackgroundResource(R.drawable.indicator_expense);
                holder.amountText.setText(String.format("-%d원",amount));
            } else if (array.get(position).getType().equals("Income")) {
                holder.indicator.setBackgroundResource(R.drawable.indicator_income);
                holder.amountText.setText(String.format("+%d원",amount));
            }

            holder.categoryText.setText(category);
            holder.dateText.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}

