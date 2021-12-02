package com.example.moneynote.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneynote.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {
    private ArrayList<String> label;
    private EditText text;
    private TextView title;
    private RecyclerView list;

    public ButtonAdapter(ArrayList<String> label, EditText text, TextView title, RecyclerView list){
        this.label = label;
        this.text = text;
        this.title = title;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Button button;

        public ViewHolder(final View view) {
            super(view);
            button = view.findViewById(R.id.button);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_container, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String categoryName = label.get(position);

        holder.button.setText(categoryName);

        holder.button.setOnClickListener(v -> {
            text.setText(categoryName);
            title.setVisibility(View.GONE);
            list.setVisibility(View.GONE);
        });
    }

    @Override
    public int getItemCount() {
        return label.size();
    }
}
