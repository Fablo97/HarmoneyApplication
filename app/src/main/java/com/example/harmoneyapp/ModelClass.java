package com.example.harmoneyapp;

public class ModelClass {
    private String imageUrl;
    private String name;
    private String assetPrice;
    private String assetSymbol;

    ModelClass(String imageUrl, String name, String assetPrice, String assetSymbol) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.assetPrice = assetPrice;
        this.assetSymbol = assetSymbol;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getAssetPrice() {
        return assetPrice;
    }

    public String getAssetSymbol() {
        return assetSymbol;
    }
}
