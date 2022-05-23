package com.example.harmoneyapp;

public class GetItemPortfolio {
    private String item_portfolio_logo;
    private String name_portfolio;
    private String item_portfolio_symbol;
    private Double item_portfolio_price;
  /*
    private String item_mymoney;
    private String item_mymoneyincrypto;
   */

    GetItemPortfolio(String item_portfolio_logo, String name_portfolio, String item_portfolio_symbol, Double item_portfolio_price, String item_mymoney, String item_mymoneyincrypto) {
        this.item_portfolio_logo = item_portfolio_logo;
        this.name_portfolio = name_portfolio;
        this.item_portfolio_symbol = item_portfolio_symbol;
        this.item_portfolio_price = item_portfolio_price;

        /*
        this.item_mymoney = item_mymoney;
        this.item_mymoneyincrypto = item_mymoneyincrypto;

       */
    }

    public String getImageUrl() {
        return item_portfolio_logo;
    }

    public String getName() {
        return name_portfolio;
    }

    public String getAssetPrice() {
        return item_portfolio_symbol;
    }

    public Double getAssetSymbol() {
        return item_portfolio_price;
    }

  /*  public String item_mymoney() {
        return item_mymoney;
    }

    public String item_mymoneyincrypto() {
        return item_mymoneyincrypto;
    }

   */
}
