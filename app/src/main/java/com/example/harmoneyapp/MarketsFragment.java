package com.example.harmoneyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import drewcarlson.coingecko.models.coins.CoinMarkets;
import drewcarlson.coingecko.models.coins.CoinMarketsList;
import drewcarlson.coingecko.models.coins.CoinPrice;


public class MarketsFragment extends Fragment {

    public MarketsFragment() {

    }

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> assetList;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_markets, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        this.initData();

        return view;
    }

    private void initData() {

        PriceViewModel viewModel = new ViewModelProvider(this).get(PriceViewModel.class);

        assetList = new ArrayList<>();
        viewModel.getCoinMarkets("eur");

        viewModel.getMarkets().observe(getViewLifecycleOwner(), markets -> {
            Log.d("", markets.toString());

            int marketsSize = markets.getMarkets().size();
            List<CoinMarkets> m = markets.getMarkets();

            for (int i = 0; i < marketsSize; i++) {

                CoinMarkets market = m.get(i);
                String name = market.getName();
                double price = market.getCurrentPrice();
                String symbol = market.getSymbol();

                assetList.add(new ModelClass(market.getImage(), name, price + "€", symbol));
                // assetList.add(new ModelClass(R.drawable.btc_logo, name, price + "€", symbol));

            }

            recyclerView.setAdapter(new Adapter(assetList));
        });

    }


}