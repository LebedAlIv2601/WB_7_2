package com.example.wb_7_2.domain.usecase

import com.example.wb_7_2.domain.model.SuperHeroModelDomain
import com.example.wb_7_2.domain.repository.SuperHeroesRepository
import javax.inject.Inject

class GetSuperHeroesListUseCase @Inject constructor(private val repository: SuperHeroesRepository) {

    suspend fun execute(): List<SuperHeroModelDomain>{
        return repository.getSuperHeroes()
    }
}