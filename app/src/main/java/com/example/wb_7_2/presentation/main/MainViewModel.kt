package com.example.wb_7_2.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.wb_7_2.utils.Resource
import com.example.wb_7_2.domain.model.SuperHeroModelDomain
import com.example.wb_7_2.domain.usecase.GetSuperHeroesListUseCase
import kotlinx.coroutines.*

class MainViewModel (private val getSuperHeroesListUseCase: GetSuperHeroesListUseCase): ViewModel() {

    private val _superHeroesList = MutableLiveData<List<SuperHeroModelDomain>>()
    val superHeroesList: LiveData<List<SuperHeroModelDomain>>
        get() = _superHeroesList

    private val _loadingPermission = MutableLiveData<Boolean>()
    val loadingPermission: LiveData<Boolean>
        get() = _loadingPermission

    init {
        _loadingPermission.value = true
    }

    fun getSuperHeroes() = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try{
            Log.e("Loading", "Trying to load data from vm")
            emit(Resource.Success(data = getSuperHeroesListUseCase.execute()))
            Log.e("Loading", "Data loaded")
        } catch(e: Exception){
            emit(Resource.Error(data = null, message = e.message ?: "Error Occurred!!!"))
        }
    }

    fun setSuperHeroesList(list: List<SuperHeroModelDomain>){
        _superHeroesList.value = list
    }

    fun changeToFalseLoadingPermission(){
        _loadingPermission.value = false
    }

}