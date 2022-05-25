package com.example.harmoneyapp;

public class GetItemPortfolio {
    private String logo;
    private String name;
    private String price;
    private String symbol;



    GetItemPortfolio(String logo, String name, String price, String symbol) {
        this.logo = logo;
        this.name = name;
        this.price = price;
        this.symbol = symbol;
    }

    public String getImageUrlPortfolio() {
        return logo;
    }

    public String getNamePortfolio() {
        return name;
    }

    public String getAssetPricePortfolio() {
        return price;
    }

    public String getAssetSymbolPortfolio() {
        return symbol;
    }
}
