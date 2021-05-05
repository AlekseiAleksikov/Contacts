package com.example.kcontacts

import android.app.Application
import com.example.kcontacts.di.AppComponent
import com.example.kcontacts.di.DaggerAppComponent
import com.example.kcontacts.di.RepositoryModule

class ContactsApplication : Application() {

    companion object {
        lateinit var graph: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        /*  graph =
               DaggerAppComponent.builder().repositoryModule(RepositoryModule())
                   .build() */
    }
}