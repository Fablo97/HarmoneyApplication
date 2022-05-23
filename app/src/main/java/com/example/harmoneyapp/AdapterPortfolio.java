package com.example.harmoneyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;


public class AdapterPortfolio extends RecyclerView.Adapter<AdapterPortfolio.ViewHolder> {

    List<GetItemPortfolio> portfolioList;

    public AdapterPortfolio(List<GetItemPortfolio> assetList) {
        this.portfolioList = assetList;
    }

    @NonNull
    @Override
    public AdapterPortfolio.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_portfolio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPortfolio.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return portfolioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name_portfolio;
        TextView item_portfolio_price;
        TextView item_portfolio_symbol;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_portfolio = itemView.findViewById(R.id.name_portfolio);
            item_portfolio_price = itemView.findViewById(R.id.item_portfolio_price);
            item_portfolio_symbol = itemView.findViewById(R.id.item_portfolio_symbol);

        }

        public void setData(String name, String price, String badges) throws IOException {


            name_portfolio.setText(name);
            item_portfolio_price.setText(price);
            item_portfolio_symbol.setText(badges.toUpperCase(Locale.ROOT));
        }
    }
}