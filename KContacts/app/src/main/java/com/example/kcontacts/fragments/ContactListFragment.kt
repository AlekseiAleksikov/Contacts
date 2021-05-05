package com.example.kcontacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.Contact
import com.example.kcontacts.R
import com.example.kcontacts.adapters.ContactAdapter
import com.example.kcontacts.presenters.ContactListPresenter
import com.example.kcontacts.views.ContactListView
import kotlinx.android.synthetic.main.fragment_contacts_list.*
import kotlinx.coroutines.Deferred
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ContactListFragment : MvpAppCompatFragment(), ContactListView {

    private val mAdapter = ContactAdapter()

    @InjectPresenter
    lateinit var contactListPresenter: ContactListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

        mAdapter.setOnClickListener(object : ContactAdapter.OnItemClickListener {
            override fun onClicked(id: Int) {
                openContact(id = id)
            }

            override fun deleteContact(id: Int) {
                mAdapter.deleteData(id)
            }

            override fun editContact(id: Int) {
                meditContact(id = id)
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                ContactListFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        contactListPresenter.fetchContacts()
        fabBtnCreateContact.setOnClickListener {
            replaceFragment(CreateContactFragment.newInstance(), false)
        }

        setupAdapter()


    }

    private fun setupAdapter() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerContactList.layoutManager = layoutManager
        recyclerContactList.adapter = mAdapter

    }
    //View implementation

    override fun presentContacts(data: List<Contact>) {
        recyclerContactList.visibility = View.VISIBLE
        txtLoading.visibility = View.GONE
        mAdapter.setData(newContacts = data)
    }

    override fun presentLoading() {
        recyclerContactList.visibility = View.GONE
        txtLoading.visibility = View.VISIBLE
    }

    override fun presentNoItems() {
        recyclerContactList.visibility = View.GONE
        txtLoading.text = "No contacts"
        txtLoading.visibility = View.VISIBLE

    }

    fun replaceFragment(fragment: Fragment, istransition: Boolean) {
        val fragmentTransition = activity!!.supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_out_right
            )
        }
        fragmentTransition.replace(R.id.frame_layout, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
        fragmentTransition.commit()
    }

    fun openContact(id: Int) {
        var fragment: Fragment
        var bundle = Bundle()
        bundle.putInt("noteId", id)
        fragment = InfoContactFragment.newInstance()
        fragment.arguments = bundle

        replaceFragment(fragment, false)

    }

    fun meditContact(id: Int) {
        var fragment: Fragment
        var bundle = Bundle()
        bundle.putInt("noteId", id)
        fragment = CreateContactFragment.newInstance()
        fragment.arguments = bundle

        replaceFragment(fragment, false)

    }


}