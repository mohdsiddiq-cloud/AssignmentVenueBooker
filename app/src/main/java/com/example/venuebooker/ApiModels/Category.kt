package com.example.venuebooker.ApiModels

import android.os.Parcelable

data class Category(
    val id: Int,
    val name: String,
    val turfs: List<Turf>,
    val venue_property_description: Any
)