package com.example.lab6_exercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MyService MyService;
    private boolean isBound;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder myBinder = (MyService.MyBinder)iBinder;
            MyService = myBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadProfile();
    }

    @Override
    protected void onDestroy() {
        saveProfile();
        super.onDestroy();
        if(isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }

    public void onStartServiceClicked(View view) {
        TextView textView = findViewById(R.id.text_view);
        textView.setText(MyService.getFirstMessage());
        MyIntentService.startActionDemo(this, "Service Stopped");
    }

    public void onShowNameClicked(View view) {
        TextView textView = findViewById(R.id.text_view);
        textView.setText(MyService.getFirstMessage());
    }

    public void loadProfile() {
        SharedPreferences preferences = getSharedPreferences("LAB6_EXERCISE_PREFERENCES", MODE_PRIVATE);
        String name_string = preferences.getString("Jared Eric", "");
        TextView textView = findViewById(R.id.text_view);
        textView.setText(name_string);
    }

    public void saveProfile() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.apply();
    }
}