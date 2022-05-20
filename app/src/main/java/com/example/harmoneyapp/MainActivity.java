package com.example.harmoneyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button logoutbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutbtn = findViewById(R.id.logoutbtn);
        mAuth = FirebaseAuth.getInstance();

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