package com.example.venuebooker.ApiModels

data class Response(
    val categories: List<Category>,
    val icon: String,
    val name: String,
    val venture_id: Int,
    val venue_id: Int
)