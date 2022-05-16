package com.example.harmoneyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText mail;
    EditText password_login;
    Button button_login;
    Button button_back;
    Button button_sign;
    Button btnLogout;
    TextView password_lost;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.mail);
        password_login = findViewById(R.id.password_login);
        button_login = findViewById(R.id.button_login);
        button_sign = findViewById(R.id.button_sign);
        button_back = findViewById(R.id.button_back);
        password_lost = findViewById(R.id.password_lost);
        btnLogout = findViewById(R.id.logoutbtn);

        mAuth = FirebaseAuth.getInstance();

        password_lost.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, LostPassword.class));
        });

        button_login.setOnClickListener(v -> {
            loginUser();
        });

        button_sign.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        } );
    }

    private void loginUser() {
        String email = mail.getText().toString();
        String password = password_login.getText().toString();

        if (TextUtils.isEmpty(email)){
            mail.setError("E-Mail cannot be empty");
            mail.requestFocus();
        }else if (TextUtils.isEmpty(password)) {
            password_login.setError("Passwort mus befüllt werden");
            password_login.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"User angemeldet", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login Fehlgeschlagen" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                }
            });
        }
    }



}