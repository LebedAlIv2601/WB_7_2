package com.example.wb_7_2.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wb_7_2.domain.usecase.GetSuperHeroesListUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val useCase: GetSuperHeroesListUseCase) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == MainViewModel::class.java)
        return MainViewModel(useCase) as T
    }
}