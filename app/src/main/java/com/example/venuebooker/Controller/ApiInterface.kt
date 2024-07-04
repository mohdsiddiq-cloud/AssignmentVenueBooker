package com.example.venuebooker.Controller

import com.example.venuebooker.ApiModels.AllTurfsResponse
import com.example.venuebooker.ApiModels.BookingRequest
import com.example.venuebooker.ApiModels.SlotsResponse
import com.example.venuebooker.ApiModels.bookingResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Date


interface ApiInterface {
    @GET("/vendor/venue/v1/turfs")
    fun getTurfs(): Call<AllTurfsResponse>

    @GET("/vendor/venue/v1/slots")
    fun getSlots(@Query("turf_id") turfId: String,@Query("date") date: String): Call<SlotsResponse>

    @POST("/vendor/venue/v1/bookslot")
    fun bookSlot(@Body bookingRequest: BookingRequest): Call<bookingResponse>

//    @POST("/vendor/venue/v1/cancelbooking")
//    fun cancelBooking(@Body cancelBookingRequest: CancelBookingRequest): Call<ResponseBody>
}