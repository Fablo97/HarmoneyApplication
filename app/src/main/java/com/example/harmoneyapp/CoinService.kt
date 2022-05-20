package com.example.harmoneyapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CoinService(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase
) {

    fun addAssetToPortfolio(amount: Number) {
        val ref = database.getReference(currentUser()!!).child("portfolio")
        ref.setValue(amount)
    }

    fun updateAssetFromPortfolio(assetId: String) {
        val ref = database.getReference(currentUser()!!).child("portfolio").child(assetId).setValue("{complete object}")
    }

    fun deleteAssetFromPortfolio() {
        val ref = database.getReference(currentUser()!!).child("portfolio")
    }

    fun getPortfolioAssets() {
        val ref = database.getReference(currentUser()!!).child("portfolio")
        ref.get()
    }

    private fun currentUser(): String? {
        return firebaseAuth.currentUser?.uid
    }

}