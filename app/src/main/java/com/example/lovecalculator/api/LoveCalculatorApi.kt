package com.example.lovecalculator.api

import com.example.lovecalculator.data.LoveCalculationResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface LoveCalculatorApi {
    @Headers(
        "X-RapidAPI-Key: 9508adf4cdmsh3e08ab8d0c50701p1a7826jsn7aa8a0297171",
        "X-RapidAPI-Host: love-calculator.p.rapidapi.com"
    )
    @GET("getPercentage")
    fun getPercentage(@Query("fname") fname: String,
                      @Query("sname") sname: String): Call<LoveCalculationResult>
}