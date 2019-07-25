package com.example.lovecalculator.api

import com.example.lovecalculator.data.LoveCalculationResult
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoveRequester {
    private val service: LoveCalculatorApi

    companion object {
        const val BASE_URL = "https://love-calculator.p.rapidapi.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(LoveCalculatorApi::class.java)
    }

    fun getPercentage(callback: Callback<LoveCalculationResult>, fname: String, sname: String) {
        val call = service.getPercentage(fname, sname)
        call.enqueue(callback)
    }
}