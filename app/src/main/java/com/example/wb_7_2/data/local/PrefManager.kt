package com.example.wb_7_2.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.wb_7_2.domain.model.SuperHeroModelDomain
import com.example.wb_7_2.utils.Constants.STORAGE_PREF_FILE
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PrefManager @Inject constructor(private val context: Context){

    private val prefs = context.getSharedPreferences(STORAGE_PREF_FILE, MODE_PRIVATE)

    private val gson = GsonBuilder().create()

    suspend fun writeHeroesToPref(heroesList: List<SuperHeroModelDomain>){

        val heroesListJson = gson.toJson(SuperHeroModelList(heroesList))

        val editor = prefs.edit()

        editor.putString(STORAGE_PREF_FILE, heroesListJson)

        editor.apply()

    }

    suspend fun getHeroesFromPref(): List<SuperHeroModelDomain>{

        val heroesListJson = prefs.getString(STORAGE_PREF_FILE, "")

        return if(heroesListJson == ""){
            emptyList()
        } else {

            gson.fromJson(heroesListJson, SuperHeroModelList::class.java).list

        }

    }

    data class SuperHeroModelList(
        val list: List<SuperHeroModelDomain>
    )

}