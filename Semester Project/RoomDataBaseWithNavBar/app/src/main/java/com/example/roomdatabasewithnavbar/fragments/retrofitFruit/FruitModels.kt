package com.example.roomdatabasewithnavbar.fragments.retrofitFruit

data class FruitModel(
    val products: ArrayList<FruitDataModel>,
)
data class FruitDataModel(
    val name: String,
)