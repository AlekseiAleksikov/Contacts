package com.example.kcontacts.views

import com.example.domain.models.Contact
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface CreateContactView : MvpView {
    fun addInfo(data : List<Contact>)
}
