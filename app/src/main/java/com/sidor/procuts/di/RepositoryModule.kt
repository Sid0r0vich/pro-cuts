package com.sidor.procuts.di

import com.sidor.procuts.data.ClientDBRepository
import com.sidor.procuts.data.ClientRepository
import com.sidor.procuts.data.CutDateDBRepository
import com.sidor.procuts.data.CutDateRepository
import com.sidor.procuts.data.CutMockRepository
import com.sidor.procuts.data.CutRepository
import com.sidor.procuts.data.UserDBRepository
import com.sidor.procuts.data.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindClientRepository(impl: ClientDBRepository): ClientRepository

    @Binds
    @Singleton
    abstract fun bindCutDateRepository(impl: CutDateDBRepository): CutDateRepository

    @Binds
    @Singleton
    abstract fun bindCutRepository(impl: CutMockRepository): CutRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserDBRepository): UserRepository
}