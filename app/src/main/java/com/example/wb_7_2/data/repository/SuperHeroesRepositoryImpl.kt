package com.example.wb_7_2.data.repository

import com.example.wb_7_2.data.local.PrefManager
import com.example.wb_7_2.data.network.SuperHeroesApiHelper
import com.example.wb_7_2.domain.model.SuperHeroModelDomain
import com.example.wb_7_2.domain.repository.SuperHeroesRepository
import javax.inject.Inject

class SuperHeroesRepositoryImpl @Inject constructor(
    private val apiHelper: SuperHeroesApiHelper,
    private val prefManager: PrefManager): SuperHeroesRepository {

    override suspend fun getSuperHeroes(): List<SuperHeroModelDomain> {

        val heroesListLocal = prefManager.getHeroesFromPref()

        return if(heroesListLocal.isEmpty()){
            val heroesListNet = apiHelper.getSuperHeroes()

            prefManager.writeHeroesToPref(heroesListNet)

            prefManager.getHeroesFromPref()
        } else {
            heroesListLocal
        }
    }
}