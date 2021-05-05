package com.example.kcontacts.di

import com.example.kcontacts.presenters.ContactListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])

interface AppComponent {

    fun inject(contactListPresenter : ContactListPresenter)
}