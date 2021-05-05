package com.example.kcontacts.presenters

import com.example.domain.repositries.implementations.ContactRepositoryImpl
import com.example.kcontacts.views.ContactListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ContactListPresenter : MvpPresenter<ContactListView>() {


    var contactsRepositoryImpl = ContactRepositoryImpl()

    fun fetchContacts() {
        viewState.presentLoading()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val contacts = contactsRepositoryImpl.fetchContacts().await()
                withContext(Dispatchers.Main) {
                    if (contacts.isEmpty()) {
                        viewState.presentNoItems()
                    } else {
                        viewState.presentContacts(data = contacts)
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()

            }
        }

    }
}