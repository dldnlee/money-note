package com.example.moneynote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.moneynote.model.UserDataModel;
import java.util.ArrayList;

public class MoneyNoteAdapter extends RecyclerView.Adapter<MoneyNoteAdapter.ViewHolder> {
    private ArrayList<UserDataModel> array;

    public MoneyNoteAdapter(ArrayList<UserDataModel> array) {
        this.array = array;
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

        holder.categoryText.setText(category);
        holder.amountText.setText(Integer.toString(amount));
        holder.descText.setText(description);

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

}
