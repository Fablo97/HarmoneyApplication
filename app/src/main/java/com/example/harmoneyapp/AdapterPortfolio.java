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

        String logo = "https://assets.coingecko.com/coins/images/1/large/solana.png?1547033579";
        String amount = portfolioList.get(position).getAmount();
        String name = portfolioList.get(position).getNamePortfolio();
        String price = portfolioList.get(position).getAssetPricePortfolio();
        String symbol = "SOL"; // portfolioList.get(position).getAssetSymbolPortfolio();
        String cryptovalue = "SOL"; // portfolioList.get(position).getAssetSymbolPortfolio();

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
        holder.porfolio_logo.setImageBitmap(bmp);
        try {
            holder.setData(logo, name, amount, price, symbol, cryptovalue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return portfolioList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView porfolio_logo;
        TextView porfolio_name;
        TextView porfolio_price;
        TextView porfolio_symbol;
        TextView porfolio_value;
        TextView porfolio_value_incrypto;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            porfolio_logo = itemView.findViewById(R.id.new_logo_portfolio);
            porfolio_name = itemView.findViewById(R.id.new_name_portfolio);
            porfolio_price = itemView.findViewById(R.id.new_price_portfolio);
            porfolio_symbol = itemView.findViewById(R.id.new_symbol_portfolio);
            porfolio_value = itemView.findViewById(R.id.new_item_mymoney);
            porfolio_value_incrypto = itemView.findViewById(R.id.new_item_mymoneyincrypto);

        }

        public void setData(String logo, String name, String amount, String price, String symbol, String cryptovalue) throws IOException {

            URL imageUrl = new URL(logo);
            Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
            porfolio_logo.setImageBitmap(bmp);

            porfolio_name.setText(name);
            porfolio_price.setText(price);
            porfolio_symbol.setText(symbol.toUpperCase(Locale.ROOT));
            porfolio_value.setText(amount);
            porfolio_value_incrypto.setText(cryptovalue);
        }
    }
}