package com.example.harmoneyapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.stats.StatsUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Set;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button change_password_button = (Button) view.findViewById(R.id.change_password_button);
        change_password_button.setOnClickListener(v -> {
            Intent change_password_intent = new Intent(getActivity(), LostPassword.class);
            startActivity(change_password_intent);
        });

        Button kill_profile_button = (Button) view.findViewById(R.id.kill_profile_button);
        kill_profile_button.setOnClickListener(v -> {

            FirebaseDatabase.getInstance().getReference().child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
                }
            });

        });

        Button impressum_button = (Button) view.findViewById(R.id.impressum_button);
        impressum_button.setOnClickListener(v -> {
            Intent impressum_intent = new Intent(getActivity(), Impressum.class);
            startActivity(impressum_intent);
        });

        Button setting_logout = (Button) view.findViewById(R.id.setting_logout);
        setting_logout.setOnClickListener(v -> {
            Intent logout_intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(logout_intent);
        });
        return view;
    }
}
