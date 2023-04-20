package com.example.ahmetweighttrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class WeightDetailActivity extends AppCompatActivity {
    private EditText weightEditText;
    private EditText dateEditText;
    private Weight chosenWeight;
    private Button deleteWeightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_detail);
        initViews();
        checkForEdit();
    }

    private void initViews() {
        weightEditText = findViewById(R.id.weightEditText);
        dateEditText = findViewById(R.id.dateEditText);
        deleteWeightBtn = findViewById(R.id.deleteBtn);
    }

    // Determines whether weight is being added or edited
    private void checkForEdit() {
        Intent intent = getIntent();

        int passedWeightID = intent.getIntExtra(Weight.weightExtra, -1);
        chosenWeight = Weight.getWeightForID(passedWeightID);

        if (chosenWeight != null) {
            weightEditText.setText(chosenWeight.getWeight());
            dateEditText.setText(chosenWeight.getDate());
        }
        // If weight being added rather than edited, the delete button won't appear
        else {
            deleteWeightBtn.setVisibility(View.INVISIBLE);
        }
    }

    // Adds or updates a new weight to the DB
    public void saveWeight(View view) {
        SQLiteDBWeights manager = SQLiteDBWeights.initDB(this);
        String weight = String.valueOf(weightEditText.getText());
        String date = String.valueOf(dateEditText.getText());

        if (chosenWeight == null) {
            int id = Weight.weightsList.size();
            Weight newWeight = new Weight(id, date, weight);
            Weight.weightsList.add(newWeight);
            manager.addWeightToDB(newWeight);
        }
        else {
            chosenWeight.setWeight(weight);
            chosenWeight.setDate(date);
            manager.updateWeight(chosenWeight);
        }
        finish();
    }

    // Sets a value to deleted field so that only non-deleted entries will show
    public void deleteWeight(View view) {
        chosenWeight.setDeleted("Deleted");
        SQLiteDBWeights manager = SQLiteDBWeights.initDB(this);
        manager.updateWeight(chosenWeight);
        finish();
    }
}