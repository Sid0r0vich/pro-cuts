package com.sidor.procuts.di

import com.sidor.procuts.data.ClientMockRepository
import com.sidor.procuts.data.ClientRepository
import com.sidor.procuts.data.CutDateMockRepository
import com.sidor.procuts.data.CutDateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {
    @Binds
    @Singleton
    abstract fun bindClientRepository(impl: ClientMockRepository): ClientRepository

    @Binds
    @Singleton
    abstract fun bindCutDateRepository(impl: CutDateMockRepository): CutDateRepository
}