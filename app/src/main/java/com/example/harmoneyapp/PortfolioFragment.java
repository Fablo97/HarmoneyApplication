package com.example.harmoneyapp;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import drewcarlson.coingecko.models.coins.CoinMarkets;


public class PortfolioFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<GetItemPortfolio> assetList2;
    Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);

        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        this.initData();

        return view;
    }

    private void initData() {

        PriceViewModel viewModel = new ViewModelProvider(this).get(PriceViewModel.class);

        assetList2 = new ArrayList<>();
        viewModel.getCoinMarkets("eur");

        viewModel.getMarkets().observe(getViewLifecycleOwner(), markets -> {
            Log.d("", markets.toString());

            int marketsSize = markets.getMarkets().size();
            List<CoinMarkets> m = markets.getMarkets();

            for (int i = 0; i < 1; i++) {

                CoinMarkets market = m.get(i);
                String name = market.getName();
                double price = market.getCurrentPrice();
                String symbol = market.getSymbol();

                assetList2.add(new GetItemPortfolio("",name,"","", "", ""));
                // assetList.add(new ModelClass(R.drawable.btc_logo, name, price + "€", symbol));
            }

            recyclerView.setAdapter(new AdapterPortfolio(assetList2));
        });


    }

}
