package com.example.harmoneyapp;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<ModelClass> assetList;

    public Adapter(List<ModelClass>assetList) {
        this.assetList=assetList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
    int assetLogo = assetList.get(position).getImageview1();
    String name=assetList.get(position).getTextview();
    String price=assetList.get(position).getTextview2();
    String badges=assetList.get(position).getTextview3();

    holder.imageView1.setImageResource(assetList.get(position).getImageview1());
    holder.setData(assetLogo, name, price, badges);

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

            imageView1=itemView.findViewById(R.id.imageView1);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);
            textView3=itemView.findViewById(R.id.textView3);

        }

        public void setData(int assetLogo, String name, String price, String badges) {
            imageView1.setImageResource(assetLogo);
            textView.setText(name);
            textView2.setText(price);
            textView3.setText(badges);
        }
    }
}
