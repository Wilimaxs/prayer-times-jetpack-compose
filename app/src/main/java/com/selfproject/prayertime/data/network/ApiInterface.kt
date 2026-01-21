package com.selfproject.prayertime.data.network

import com.selfproject.prayertime.data.model.PrayerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/timings/{date}")
    suspend fun getTimingsByCity(
        @Path("date") date: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("method") method: Int = 20, // Kemenag Standard Indonesia
    ): Response<PrayerResponse>
}