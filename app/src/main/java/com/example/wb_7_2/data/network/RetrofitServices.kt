package com.example.wb_7_2.data.network

import com.example.wb_7_2.data.model.SuperHeroModelData
import retrofit2.http.GET

interface RetrofitServices {

    @GET("get_marvel_character/")
    suspend fun getSuperHeroes(): List<SuperHeroModelData>

}