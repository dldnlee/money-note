package com.example.moneynote.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneynote.R;
import com.example.moneynote.models.UserDataModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MoneyNoteAdapter extends RecyclerView.Adapter<MoneyNoteAdapter.ViewHolder> {
    private ArrayList<UserDataModel> array;
    private ArrayList<UserDataModel> dataArray;
    private Context context;

    public MoneyNoteAdapter(Context context,ArrayList<UserDataModel> array, ArrayList<UserDataModel> dataArray) {
        this.array = array;
        this.dataArray = dataArray;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryText, amountText, descText;

        public ViewHolder(final View view){
            super(view);
            categoryText = view.findViewById(R.id.category);
            amountText = view.findViewById(R.id.amount);
            descText = view.findViewById(R.id.description);
        }
    }

    @NonNull
    @Override
    public MoneyNoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_expense_container, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoneyNoteAdapter.ViewHolder holder, int position) {
        String category = array.get(position).getCategory();
        int amount = array.get(position).getAmount();
        String description = array.get(position).getDescription();

        if (array.get(position).getType().equals("Expense")) {
            holder.amountText.setTextColor(Color.GRAY);
            holder.amountText.setText("-"+Integer.toString(amount));
        } else if (array.get(position).getType().equals("Income")) {
            holder.amountText.setTextColor(Color.RED);
            holder.amountText.setText("+"+Integer.toString(amount));
        }

        holder.categoryText.setText(category);
        holder.descText.setText(description);

        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("기록 삭제");
            alertDialog.setMessage("해당 기록을 삭제 하시겠습니다?");
            alertDialog.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alertDialog.setNegativeButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    for (int k=0; k<dataArray.size(); k++) {
                        if (dataArray.get(k).equals(array.get(position))) {
                            dataArray.remove(k);
                        }
                    }
                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}
