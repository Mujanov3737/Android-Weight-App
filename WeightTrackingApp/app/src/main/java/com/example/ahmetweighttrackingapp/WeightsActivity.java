package com.example.ahmetweighttrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class WeightsActivity extends AppCompatActivity {

    private ListView weightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyweights);
        initList();
        pullFromDB();
        setConfig();
        setOnItemClickListener();
    }

    // Initializing static arraylist of weights to the list view
    private void initList() {
        weightList = findViewById(R.id.weightsListView);
    }

    // Initializing and populating database
    private void pullFromDB() {
        SQLiteDBWeights manager = SQLiteDBWeights.initDB(this);
        manager.popWeightList();
    }

    // Used to display our weight/date items in the list view by setting the configured adapter
    private void setConfig() {
        CellConfig cellConfig = new CellConfig(getApplicationContext(), Weight.unDeletedNodes());
        weightList.setAdapter(cellConfig);
    }

    // Launching the edit activity when a weight node is clicked
    private void setOnItemClickListener() {
        weightList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Weight chosenWeight = (Weight) weightList.getItemAtPosition(i);
                Intent editWeight = new Intent(getApplicationContext(), WeightDetailActivity.class);
                editWeight.putExtra(Weight.weightExtra, chosenWeight.getId());
                startActivity(editWeight);
            }
        });
    }

    // Launch add weight activity when the add button is pressed
    public void addWeight(View view) {
        Intent weightIntent = new Intent(this, WeightDetailActivity.class);
        startActivity(weightIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setConfig();
    }
}