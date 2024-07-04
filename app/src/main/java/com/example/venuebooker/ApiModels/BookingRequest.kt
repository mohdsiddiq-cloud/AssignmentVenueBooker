package com.example.venuebooker.ApiModels

data class BookingRequest(
    val slots: List<Slot>,
    val user_first_name: String,
    val user_last_name: String,
    val user_email: String,
    val user_dial_code: String,
    val user_mobile_no: String
)