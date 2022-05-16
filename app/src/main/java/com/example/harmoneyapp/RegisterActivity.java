package com.example.harmoneyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText mail;
    EditText password_register;
    EditText password_valid;
    Button button_back;
    Button button_newuser;
    TextView password_lost;
    TextView btnLogout;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mail = findViewById(R.id.mail);
        password_register = findViewById(R.id.password_register);
        password_valid = findViewById(R.id.password_valid);
        button_back = findViewById(R.id.button_back);
        button_newuser = findViewById(R.id.button_newuser);
        password_lost = findViewById(R.id.password_lost);
        btnLogout = findViewById(R.id.logoutbtn);

        mAuth = FirebaseAuth.getInstance();

        button_newuser.setOnClickListener(view -> {
            createUser();
        });

        button_back.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }


    private void createUser() {
    String email = mail.getText().toString();
    String password = password_register.getText().toString();
    String passwordValid = password_register.getText().toString();


        if (TextUtils.isEmpty(email)){
        mail.setError("E-Mail cannot be empty");
        mail.requestFocus();
    }else if (TextUtils.isEmpty(password)) {
        password_register.setError("Passwort mus befüllt werden");
        password_register.requestFocus();
    }else if (password.equals(passwordValid)){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()) {
                Toast.makeText(RegisterActivity.this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            } else {
                Toast.makeText(RegisterActivity.this, "Registrieren Fehlgeschlagen, Passwörter müssen authentisch sein" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        });
    }}
}