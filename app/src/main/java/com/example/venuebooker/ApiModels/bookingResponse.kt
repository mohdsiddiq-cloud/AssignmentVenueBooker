package com.example.venuebooker.ApiModels

data class bookingResponse(
    val code: Int,
    val message: String,
    val metadata: List<Any>,
    val requestLocation: String,
    val response: ResponseXX,
    val success: Boolean
)