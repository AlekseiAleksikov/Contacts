package com.example.kcontacts.di

import com.example.domain.repositries.implementations.ContactRepositoryImpl
import com.example.kcontacts.presenters.ContactListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepositoryModule() : ContactRepositoryImpl = ContactRepositoryImpl()
}