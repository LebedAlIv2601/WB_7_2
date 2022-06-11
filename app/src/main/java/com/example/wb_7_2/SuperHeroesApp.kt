package com.example.wb_7_2

import android.app.Application
import android.content.Context
import com.example.wb_7_2.di.AppComponent
import com.example.wb_7_2.di.DaggerAppComponent
import com.example.wb_7_2.di.DataModule

class SuperHeroesApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().dataModule(DataModule(context = this)).build()
    }
}

val Context.appComponent: AppComponent
    get() = when(this){
        is SuperHeroesApp -> appComponent
        else -> applicationContext.appComponent
    }