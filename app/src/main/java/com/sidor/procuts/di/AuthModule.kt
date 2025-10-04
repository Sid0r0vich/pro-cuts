package com.sidor.procuts.di

import com.example.wellness.auth.Auth
import com.example.wellness.auth.FirebaseAuth
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun bindAuth(impl: FirebaseAuth): Auth
}