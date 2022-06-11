package com.example.wb_7_2.domain.repository

import com.example.wb_7_2.domain.model.SuperHeroModelDomain

interface SuperHeroesRepository {

    suspend fun getSuperHeroes(): List<SuperHeroModelDomain>

}