package com.example.moneynote.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneynote.MainActivity;
import com.example.moneynote.R;
import com.example.moneynote.fragments.CalendarFragment;
import com.example.moneynote.models.UserDataModel;
import com.example.moneynote.utils.EventDecorator;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ArrayList<UserDataModel> array;
    private ArrayList<UserDataModel> dataArray;
    private Context context;
    private Fragment fragment;
    private MaterialCalendarView calendarView;

    public CalendarAdapter(Context context, ArrayList<UserDataModel> array, ArrayList<UserDataModel> dataArray, MaterialCalendarView calendarView) {
        this.array = array;
        this.dataArray = dataArray;
        this.context = context;
        this.calendarView = calendarView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryText, amountText, descText;
        private View indicator;

        public ViewHolder(final View view){
            super(view);
            categoryText = view.findViewById(R.id.category);
            amountText = view.findViewById(R.id.amount);
            descText = view.findViewById(R.id.desc);
            indicator = view.findViewById(R.id.indicator);
        }
    }

    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_expense_container, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        String category = array.get(position).getCategory();
        int amount = array.get(position).getAmount();
        String description = array.get(position).getDescription();

        if (array.get(position).getType().equals("Expense")) {
            if(description.equals("")) {
                holder.descText.setVisibility(View.GONE);
            } else {
                holder.descText.setVisibility(View.VISIBLE);
            }
            holder.descText.setText(description);
            holder.indicator.setBackgroundResource(R.drawable.indicator_expense);
            holder.amountText.setText("-"+Integer.toString(amount));
        } else if (array.get(position).getType().equals("Income")) {
            if(description.equals("")) {
                holder.descText.setVisibility(View.GONE);
            } else {
                holder.descText.setVisibility(View.VISIBLE);
            }
            holder.descText.setText(description);
            holder.indicator.setBackgroundResource(R.drawable.indicator_income);
            holder.amountText.setText("+"+Integer.toString(amount));
        }



        holder.categoryText.setText(category);

        holder.itemView.setOnLongClickListener(v -> {
            holder.itemView.setBackgroundColor(Color.parseColor("#BDBDBD"));
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("기록 삭제");
            alertDialog.setMessage("해당 기록을 삭제 하시겠습니다?");
            alertDialog.setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                }
            });
            alertDialog.setNegativeButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    for (int k=0; k<dataArray.size(); k++) {
                        if (dataArray.get(k).equals(array.get(position))) dataArray.remove(k);
                    }
                    holder.itemView.setVisibility(View.GONE);
                    calendarView.invalidateDecorators();
                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.show();
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}
