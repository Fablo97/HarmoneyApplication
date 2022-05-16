package com.example.harmoneyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LostPassword extends AppCompatActivity {


    private EditText lost_mail;
    private Button button_reset;
    private Button button_back;
    private ProgressBar loading;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_password);

        lost_mail = (EditText) findViewById(R.id.lost_mail);
        button_reset = (Button) findViewById(R.id.button_reset);
        loading = (ProgressBar) findViewById(R.id.loading);
        button_back = (Button) findViewById(R.id.button_back);


        mAuth = FirebaseAuth.getInstance();

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPasswort();
            }
        });

        button_back.setOnClickListener(view -> {
            startActivity(new Intent(LostPassword.this, LoginActivity.class));
        });

    }

    private void resetPasswort() {
        String mail_reset = lost_mail.getText().toString().trim();

        if (mail_reset.isEmpty()) {
            lost_mail.setError("Bitte E-mail eintragen");
            lost_mail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(mail_reset).matches()) {
            lost_mail.setError("Falsche E-Mail");
            lost_mail.requestFocus();
            return;
        }
            loading.setVisibility(View.VISIBLE);
            mAuth.sendPasswordResetEmail(mail_reset).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LostPassword.this, "Check your E-Mail", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LostPassword.this, "Try again", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


