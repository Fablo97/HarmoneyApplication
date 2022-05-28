package com.example.harmoneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Impressum extends AppCompatActivity {

    Button button_back_impressum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressum);

        button_back_impressum = findViewById(R.id.button_back_impressum);

        button_back_impressum.setOnClickListener(v -> {
            startActivity(new Intent(Impressum.this, MainActivity.class));
        });

    }
}