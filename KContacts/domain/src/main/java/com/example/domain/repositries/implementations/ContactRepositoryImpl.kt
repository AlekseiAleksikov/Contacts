package com.example.domain.repositries.implementations

import com.example.domain.models.Contact
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ContactRepositoryImpl {

    var mockData = ArrayList<Contact>()

    fun addContact(): List<Contact> {
        mockData.add(
                Contact(
                        id = 0,
                        surname = "Aleksikov",
                        name = "Aleksei",
                        phone = "+79315365929",
                        icon = ""
                )
        )
        return mockData
    }

    suspend fun fetchContacts(): Deferred<List<Contact>> {

        Thread.sleep(1000)

       /* mockData.add(
                Contact(
                        id = 0,
                        surname = "Aleksikov",
                        name = "Aleksei",
                        phone = "+79315365929",
                        icon = ""
                )
        )
        mockData.add(
                Contact(
                        id = 1,
                        surname = "Habibullin",
                        name = "Leonid",
                        phone = "+79315121212",
                        icon = ""
                )
        )
        mockData.add(
                Contact(
                        id = 2,
                        surname = "Aleksikova",
                        name = "Olga",
                        phone = "+793153651234",
                        icon = ""
                )
        ) */

        return GlobalScope.async { mockData }

    }
}