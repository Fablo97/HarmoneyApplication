package com.example.harmoneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChangePassword extends AppCompatActivity {

    Button button_back_change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        button_back_change_password = findViewById(R.id.button_back_change_password);

        button_back_change_password.setOnClickListener(v -> {
            startActivity(new Intent(ChangePassword.this, MainActivity.class));
        });
    }
}