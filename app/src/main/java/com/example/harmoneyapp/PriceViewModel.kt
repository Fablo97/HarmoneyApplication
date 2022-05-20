package com.example.harmoneyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import drewcarlson.coingecko.CoinGeckoClient
import drewcarlson.coingecko.models.coins.CoinPrice
import androidx.lifecycle.viewModelScope
import drewcarlson.coingecko.models.coins.CoinMarkets
import drewcarlson.coingecko.models.coins.CoinMarketsList
import kotlinx.coroutines.launch


class PriceViewModel() : ViewModel() {

    private val _coinPrices = MutableLiveData<Map<String, CoinPrice>>()
    private val _coinMarkets = MutableLiveData<CoinMarketsList>()

    private val coinGecko = CoinGeckoClient.create()

    val coinPrices: LiveData<Map<String, CoinPrice>>
        get() = _coinPrices

    val markets: LiveData<CoinMarketsList>
        get() = _coinMarkets

    fun getTokenPrices(ids: String, currencies: String) {
        viewModelScope.launch {
            _coinPrices.value = coinGecko.getPrice(
                ids, currencies,
                includeMarketCap = true,
                include24hrVol = true,
                include24hrChange = true
            )
        }
    }

    fun getCoinMarkets(currency: String) {
        viewModelScope.launch {
            _coinMarkets.value = coinGecko.getCoinMarkets(currency, sparkline = true)
        }
    }

}


data class Assets(
    var assetName: String? = null,
    var assetSymbol: String? = null
)