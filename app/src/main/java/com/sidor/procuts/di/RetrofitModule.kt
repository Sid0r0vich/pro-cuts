package com.sidor.procuts.di

import com.sidor.procuts.network.db.DBRetrofit
import com.sidor.procuts.network.db.GinApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return DBRetrofit
    }

    @Provides
    @Singleton
    fun provideGinApiService(retrofit: Retrofit): GinApiService {
        return retrofit.create(GinApiService::class.java)
    }
}