package com.example.wb_7_2.data.network


import com.example.wb_7_2.data.utils.toDomain
import com.example.wb_7_2.domain.model.SuperHeroModelDomain

import javax.inject.Inject

class SuperHeroesApiHelper @Inject constructor(private val services: RetrofitServices) {

    suspend fun getSuperHeroes(): List<SuperHeroModelDomain>{

        return services.getSuperHeroes().map { it.toDomain() }

    }

}