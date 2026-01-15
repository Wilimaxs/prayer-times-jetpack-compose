package com.selfproject.prayertime.data.network

import com.selfproject.prayertime.data.model.PrayerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("v1/timingsByCity")
    suspend fun getTimingsByCity(
        @Query("city") city: String,
        @Query("country") country: String,
        @Query("method") method: Int = 20, // Kemenag Standard Indonesia
        @Query("shafaq") shafaq: String = "general"
    ): Response<PrayerResponse>
}