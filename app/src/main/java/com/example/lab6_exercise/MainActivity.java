package com.example.lab6_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearProfile();
    }

    public void onStartServiceClicked(View view) {
        MyIntentService.startActionDemo(this, "Service Stopped.");
    }

    public void onShowNameClicked(View view) {
        loadProfile();
    }

    public void clearProfile() {
        SharedPreferences preferences = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public void loadProfile() {
        SharedPreferences preferences = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
        String name_string = preferences.getString("name", "Name not yet ready");
        TextView textView = findViewById(R.id.text_view);
        textView.setText(name_string);
    }
}