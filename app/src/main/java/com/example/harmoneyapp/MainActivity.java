package com.example.harmoneyapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button logoutbtn;
    FirebaseAuth mAuth;
    FloatingActionButton add_button;
    TextView spinnerserchasset;
    ArrayList<String> asset_spinner_list;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutbtn = findViewById(R.id.logoutbtn);
        mAuth = FirebaseAuth.getInstance();

        add_button = findViewById(R.id.addButton);





        add_button.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddAssetActivity.class));
        });

        logoutbtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationBar);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.marketFragment:
                        FragmentTransaction homeAction = getSupportFragmentManager().beginTransaction();
                        homeAction.replace(R.id.keyFragment, new MarketsFragment());
                        homeAction.commit();
                        return true;
                    case R.id.portfolioFragment:
                        FragmentTransaction portfolioAction = getSupportFragmentManager().beginTransaction();
                        portfolioAction.replace(R.id.keyFragment, new PortfolioFragment());
                        portfolioAction.commit();
                        return true;
                    case R.id.newsFragment:
                        FragmentTransaction newsAction = getSupportFragmentManager().beginTransaction();
                        newsAction.replace(R.id.keyFragment, new NewsFragment());
                        newsAction.commit();
                        return true;
                    case R.id.settingsFragmnet:
                        FragmentTransaction settingsAction = getSupportFragmentManager().beginTransaction();
                        settingsAction.replace(R.id.keyFragment, new SettingsFragment());
                        settingsAction.commit();
                        return  true;
                }
                return true;
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}