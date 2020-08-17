package com.example.scp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    public JsonParse jsonParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        String names = intent.getStringExtra("names");

        jsonParse = new JsonParse(names);
        String className = jsonParse.classcode();
        String monday = jsonParse.parse("monday");
        String tuesday = jsonParse.parse("tuesday");
        String wednesday = jsonParse.parse("wednesday");
        String thursday = jsonParse.parse("thursday");
        String friday = jsonParse.parse("friday");

        TextView classView = findViewById(R.id.className);
        TextView mondayView = findViewById(R.id.monday);
        TextView tuesdayView = findViewById(R.id.tuesday);
        TextView wednesdayView = findViewById(R.id.wednesday);
        TextView thursdayView = findViewById(R.id.thursday);
        TextView fridayView = findViewById(R.id.friday);

        classView.setText(className);
        mondayView.setText(monday);
        tuesdayView.setText(tuesday);
        wednesdayView.setText(wednesday);
        thursdayView.setText(thursday);
        fridayView.setText(friday);

    }

    public void logout_onClick(View v) {
        finish();
    }

}
