package com.example.venuebooker.ApiModels

data class AllTurfsResponse(
    val code: Int,
    val message: String,
    val metadata: Metadata,
    val requestLocation: String,
    val response: List<Response>,
    val success: Boolean
)