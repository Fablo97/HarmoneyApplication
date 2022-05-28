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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ModelClass> assetList;

    public Adapter(List<ModelClass> assetList) {
        this.assetList = assetList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);

        String assetLogo = assetList.get(position).getImageUrl();
        String name = assetList.get(position).getName();
        String price = assetList.get(position).getAssetPrice();
        String badges = assetList.get(position).getAssetSymbol();

        URL imageUrl = null;
        try {
            imageUrl = new URL(assetLogo);
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
       holder.imageView1.setImageBitmap(bmp);

        try {
            holder.setData(assetLogo, name, price, badges);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return assetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        TextView textView;
        TextView textView2;
        TextView textView3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView1 = itemView.findViewById(R.id.new_logo_markets);
            textView = itemView.findViewById(R.id.new_name_markets);
            textView2 = itemView.findViewById(R.id.new_price_markets);
            textView3 = itemView.findViewById(R.id.new_symbol_markets);

        }

        public void setData(String assetLogo, String name, String price, String badges) throws IOException {

            URL imageUrl = new URL(assetLogo);
            Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
            imageView1.setImageBitmap(bmp);
            // imageView1.setImageResource(assetLogo);


            textView.setText(name);
            textView2.setText(price);
            textView3.setText(badges.toUpperCase(Locale.ROOT));
        }
    }
}
