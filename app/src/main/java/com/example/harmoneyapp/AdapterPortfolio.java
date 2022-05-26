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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;


public class AdapterPortfolio extends RecyclerView.Adapter<AdapterPortfolio.ViewHolder> {

    List<GetItemPortfolio> portfolioList;

    public AdapterPortfolio(List<GetItemPortfolio> portfolioList) {
        this.portfolioList = portfolioList;
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



       // String logo = portfolioList.get(position).getImageUrlPortfolio() || "SOL";
        // TODO: get image url from firestore
        String logo = "https://assets.coingecko.com/coins/images/1/large/solana.png?1547033579";


        String amount = portfolioList.get(position).getAmount();
        String name = portfolioList.get(position).getNamePortfolio();
        String price = portfolioList.get(position).getAssetPricePortfolio();

        // TODO: get correct symbol
        String symbol = "SOL"; // portfolioList.get(position).getAssetSymbolPortfolio();


        URL imageUrl = null;
        try {
            imageUrl = new URL(logo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            assert imageUrl != null;
            bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.test.setImageBitmap(bmp);
        try {
            holder.setData(logo, name, amount, price, symbol);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return portfolioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView test;
        TextView asset_name;
        TextView asset_price;
        TextView asset_symbol;
        TextView item_mymoney;
        TextView item_mymoneyincrypto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            test = itemView.findViewById(R.id.new_portfolio_logo);
            asset_name = itemView.findViewById(R.id.new_name_portfolio);
            asset_price = itemView.findViewById(R.id.new_portfolio_price);
            asset_symbol = itemView.findViewById(R.id.new_portfolio_symbol);

            item_mymoneyincrypto = itemView.findViewById(R.id.new_item_mymoneyincrypto);

        }

        public void setData(String logo, String name, String amount, String price, String symbol) throws IOException {

            URL imageUrl = new URL(logo);
            Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
            test.setImageBitmap(bmp);

            asset_name.setText(name);
            asset_price.setText(price);
            asset_symbol.setText(symbol.toUpperCase(Locale.ROOT));

            item_mymoneyincrypto.setText(amount);
        }
    }
}