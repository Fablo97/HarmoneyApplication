package com.example.harmoneyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class AdapterPortfolio extends RecyclerView.Adapter<AdapterPortfolio.ViewHolder> {

    List<GetItemPortfolio> assetList2;


    public AdapterPortfolio(List<GetItemPortfolio> assetList2) {
        this.assetList2 = assetList2;
    }

    @NonNull
    @Override
    public AdapterPortfolio.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_portfolio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPortfolio.ViewHolder holder, int position) {


        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        String assetLogo = assetList2.get(position).getImageUrl();
        String name = assetList2.get(position).getName();
        String price = assetList2.get(position).getAssetPrice();
        String badges = assetList2.get(position).getAssetSymbol();

    }

    @Override
    public int getItemCount() {
        return assetList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_portfolio_logo;
        TextView name_portfolio;
        TextView item_portfolio_symbol;
        TextView item_portfolio_price;
   //     TextView item_mymoney;
     //   TextView item_mymoneyincrypto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_portfolio_logo = itemView.findViewById(R.id.item_portfolio_logo);
            name_portfolio = itemView.findViewById(R.id.name_portfolio);
            item_portfolio_symbol = itemView.findViewById(R.id.item_portfolio_symbol);
            item_portfolio_price = itemView.findViewById(R.id.item_portfolio_price);
       //     item_mymoney = itemView.findViewById(R.id.item_mymoney);
         //   item_mymoneyincrypto = itemView.findViewById(R.id.item_mymoneyincrypto);

        }


    }
}
