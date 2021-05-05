package com.example.kcontacts.presenters

import com.example.domain.repositries.implementations.ContactRepositoryImpl
import com.example.kcontacts.views.ContactListView
import com.example.kcontacts.views.CreateContactView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class CreateContactPresenter : MvpPresenter<CreateContactView>() {

    var contactsRepositoryImpl = ContactRepositoryImpl()

    fun addContact() {
        val contacts = contactsRepositoryImpl.addContact()
        viewState.addInfo(data = contacts)

    }

}