package com.example.venuebooker.ApiModels

data class ResponseX(
    val available: Boolean,
    val base_price: String,
    val end_time: String,
    val start_time: String
)