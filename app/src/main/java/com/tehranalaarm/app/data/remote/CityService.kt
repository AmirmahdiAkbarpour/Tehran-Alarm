package com.tehranalaarm.app.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query

interface CityService {

    @GET("api/v1/cities")
    suspend fun getAllCities(): List<CityResponse>

    @GET("api/v1/cities/{cityId}")
    suspend fun getCity(@Path("cityId") cityId: String): CityResponse
}

data class CityResponse(
    val id: String,
    val name: String,
    val nameEn: String,
    val province: String,
    val latitude: Double,
    val longitude: Double,
    val population: Long
)
