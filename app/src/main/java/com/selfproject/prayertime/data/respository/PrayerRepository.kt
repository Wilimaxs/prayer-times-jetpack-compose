package com.selfproject.prayertime.data.respository

import com.selfproject.prayertime.data.common.Resource
import com.selfproject.prayertime.data.model.PrayerData
import com.selfproject.prayertime.data.network.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrayerRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    fun getTimingsByCity(city: String, country: String): Flow<Resource<PrayerData>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiInterface.getTimingsByCity(city, country)

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Resource.Success(body.data))
                } else {
                    emit(Resource.Error("No data found"))
                }
            } else {
                emit(Resource.Error("something went wrong ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Connection error: ${e.message ?: "Unknown error"}"))
        }
    }.flowOn(Dispatchers.IO)

}