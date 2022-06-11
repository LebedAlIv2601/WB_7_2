package com.example.wb_7_2.di

import android.content.Context
import com.example.wb_7_2.data.local.PrefManager
import com.example.wb_7_2.data.network.RetrofitServices
import com.example.wb_7_2.data.repository.SuperHeroesRepositoryImpl
import com.example.wb_7_2.domain.repository.SuperHeroesRepository
import com.example.wb_7_2.presentation.main.MainFragment
import com.example.wb_7_2.utils.Constants.BASE_API
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [DataModule::class, DomainBinds::class])
interface AppComponent{
    fun inject(mainFragment: MainFragment)
}

@Module
class DataModule (private val context: Context){

    @Provides
    fun provideContext(): Context{
        return context
    }

    @Provides
    fun getPrefManager(context: Context): PrefManager{
        return PrefManager(context)
    }

    @Provides
    fun provideServices(): RetrofitServices {
        val retrofit  = Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitServices::class.java)
    }
}

@Module
interface DomainBinds{

    @Binds
    fun superHeroesRepositoryImplToInterface(
        repositoryImpl: SuperHeroesRepositoryImpl
    ): SuperHeroesRepository

}
