package com.example.pruebamovilapp.data.model

data class PointOfSale(
    val id: Int,
    val name: String,
    val code: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
)