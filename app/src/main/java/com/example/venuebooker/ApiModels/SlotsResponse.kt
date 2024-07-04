package com.example.venuebooker.ApiModels

data class SlotsResponse(
    val code: Int,
    val message: String,
    val metadata: MetadataX,
    val requestLocation: String,
    val response: List<ResponseX>,
    val success: Boolean
)