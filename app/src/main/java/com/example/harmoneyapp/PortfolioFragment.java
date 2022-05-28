package com.example.harmoneyapp;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import static android.content.ContentValues.TAG;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;


import java.util.ArrayList;
import java.util.List;

import drewcarlson.coingecko.models.coins.CoinMarkets;


public class PortfolioFragment extends Fragment {

    public PortfolioFragment() {

    }

    FirebaseAuth mAuth;
    FirebaseFirestore db;
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

        viewModel.getCoinMarkets("eur");

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        portfolioList = new ArrayList<>();

        assert user != null;
        DocumentReference docRef = db.collection("users").document(user.getUid()).collection("portfolio").document("asset_list");
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                    Map<String, Object> data = document.getData();

                    assert data != null;
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();

                        portfolioList.add(new GetItemPortfolio(key, value.toString(), key,"price", key));
                        //   portfolioList.add(new GetItemPortfolio("name",name,"",symbol));

                    }
            //   portfolioList.add(new GetItemPortfolio("name",name,"",symbol));
                    //   portfolioList.add(new GetItemPortfolio(market.getImage(), name, price + "€", symbol));

                    recyclerView2.setAdapter(new AdapterPortfolio(portfolioList));
                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
    }

}
