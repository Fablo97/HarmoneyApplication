package com.example.harmoneyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import drewcarlson.coingecko.models.coins.CoinMarkets;

public class AddAssetActivity extends AppCompatActivity {

    Button button;

    TextView spinnerserchasset;
    ArrayList<String> asset_spinner_list;
    List<ModelClass> assetList;
    Dialog dialog;
    Button add_portfolio_button;
    EditText add_asset_number;

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    String userID;

    Spinner spinnersearch;
    EditText my_add_asset_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);

        button = findViewById(R.id.button_back_add);
        add_portfolio_button = findViewById(R.id.add_portfolio_button);
        add_asset_number = findViewById(R.id.add_asset_number);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        add_portfolio_button.setOnClickListener(v -> {
            String spinner_data = spinnerserchasset.getText().toString();
            String add_asset_number_data = add_asset_number.getText().toString();

            userID = mAuth.getCurrentUser().getUid();
            DocumentReference documentReference = firestore.collection("users").document(userID).collection("portfolio").document("asset_list");
            Map<String,Object> user = new HashMap<>();
            user.put(spinner_data, add_asset_number_data);

            documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
               @Override
                public void onSuccess(Void unused) {
                    Log.d("", "user is created for"+userID);
                    startActivity(new Intent(AddAssetActivity.this, MainActivity.class));
                }
            });
           Toast.makeText(AddAssetActivity.this, spinner_data+add_asset_number_data, Toast.LENGTH_SHORT).show();
        });

        button.setOnClickListener(v -> {
           startActivity(new Intent(AddAssetActivity.this, MainActivity.class));
        });

        PriceViewModel viewModel = new ViewModelProvider(this).get(PriceViewModel.class);

        spinnerserchasset = findViewById(R.id.spinnersearch);
        asset_spinner_list = new ArrayList<>();

        viewModel.getCoinMarkets("eur");
        viewModel.getMarkets().observe(this, markets -> {
            Log.d("", markets.toString());

            int marketsSize = markets.getMarkets().size();
            List<CoinMarkets> m = markets.getMarkets();

            for (int i = 0; i < marketsSize; i++) {

                CoinMarkets market = m.get(i);
                String name = market.getName();
                double price = market.getCurrentPrice();
                String symbol = market.getSymbol();

                asset_spinner_list.add(name);
                // assetList.add(new ModelClass(R.drawable.btc_logo, name, price + "€", symbol));
            }
        });



        spinnerserchasset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(AddAssetActivity.this);

                dialog.setContentView(R.layout.selectfield_add);

                dialog.getWindow().setLayout(650, 800);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(AddAssetActivity.this,
                        android.R.layout.simple_list_item_1,asset_spinner_list);

                listView.setAdapter(adapter);

                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        spinnerserchasset.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });
    }

}