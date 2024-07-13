package com.example.medicine.retrofit

import com.example.medicine.database.MedicineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiService {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("2cca700c-f8fd-47f9-99d4-3cddb86846cd")
    suspend fun getList(): Response<MedicineResponse>


}
