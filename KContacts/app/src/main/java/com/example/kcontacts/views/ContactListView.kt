package com.example.kcontacts.views

import com.example.domain.models.Contact
import kotlinx.coroutines.Deferred
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ContactListView : MvpView {
    fun presentContacts(data : List<Contact>)
    fun presentLoading()
    fun presentNoItems()

}