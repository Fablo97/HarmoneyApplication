package com.example.harmoneyapp;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import drewcarlson.coingecko.models.coins.CoinMarkets;


public class PortfolioFragment extends Fragment {

    public PortfolioFragment() {

    }

    RecyclerView recyclerView2;
    List<GetItemPortfolio> portfolioList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_portfolio, container, false);

        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        this.initData();

        return view;
    }

    private void initData() {
        PriceViewModel viewModel = new ViewModelProvider(this).get(PriceViewModel.class);

        portfolioList = new ArrayList<>();
        viewModel.getCoinMarkets("eur");


        viewModel.getMarkets().observe(getViewLifecycleOwner(), markets -> {
            Log.d("", markets.toString());

            int marketsSize = markets.getMarkets().size();
            List<CoinMarkets> m = markets.getMarkets();

            for (int i = 0; i < 5; i++) {
                CoinMarkets market = m.get(i);
                String name = market.getName();
                double price = market.getCurrentPrice();
                String symbol = market.getSymbol();

                portfolioList.add(new GetItemPortfolio(name, symbol, "price",symbol));

        //        portfolioList.add(new GetItemPortfolio("name",name,"",symbol));
             //   portfolioList.add(new GetItemPortfolio(market.getImage(), name, price + "€", symbol));

            }

            recyclerView2.setAdapter(new AdapterPortfolio(portfolioList));
        });
    }

}
