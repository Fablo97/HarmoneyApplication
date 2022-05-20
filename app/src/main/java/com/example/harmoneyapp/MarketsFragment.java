package com.example.harmoneyapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MarketsFragment extends Fragment {

    public MarketsFragment() {

    }

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass>assetList;
    Adapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_markets, container, false);

        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

      //  initData();

        recyclerView.setAdapter(new Adapter(initData()));

        return view;
    }

    private List<ModelClass> initData() {

        assetList = new ArrayList<>();
        assetList.add(new ModelClass(R.drawable.btc_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.eth_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.bnb_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.ada_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.xrp_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.sol_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.avax_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.doge_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.dot_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.btc_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.btc_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.btc_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.btc_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.btc_logo, "Bitcoin","33000 $", "BTC"));
        assetList.add(new ModelClass(R.drawable.btc_logo, "Bitcoin","33000 $", "BTC"));


        return assetList;
    }


}