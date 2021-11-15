package com.example.moneynote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import org.eazegraph.lib.charts.PieChart;

public class GraphActivity extends AppCompatActivity {
    PieChart pieChart;
    EditText Product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        pieChart = findViewById(R.id.piechart);
        setData();
        
    }
    private void setData(){
        pieChart.startAnimation();
    }
}