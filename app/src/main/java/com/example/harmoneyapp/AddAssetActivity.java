package com.example.harmoneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AddAssetActivity extends AppCompatActivity {

    Button button;

    TextView spinnerserchasset;
    ArrayList<String> asset_spinner_list;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);

        button = findViewById(R.id.button_back_add);

        button.setOnClickListener(v -> {
           startActivity(new Intent(AddAssetActivity.this, MainActivity.class));
        });

        spinnerserchasset = findViewById(R.id.spinnersearch);
        asset_spinner_list = new ArrayList<>();
        asset_spinner_list.add("Bitcoin");
        asset_spinner_list.add("Ethereum");
        asset_spinner_list.add("Cardano");
        asset_spinner_list.add("XRP");

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