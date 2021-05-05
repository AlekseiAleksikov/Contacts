package com.example.kcontacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.domain.models.Contact
import com.example.domain.repositries.implementations.ContactRepositoryImpl
import com.example.kcontacts.R
import com.example.kcontacts.adapters.ContactAdapter
import com.example.kcontacts.presenters.ContactListPresenter
import com.example.kcontacts.presenters.CreateContactPresenter
import com.example.kcontacts.views.ContactListView
import com.example.kcontacts.views.CreateContactView
import kotlinx.android.synthetic.main.fragment_create_contact.*
import kotlinx.android.synthetic.main.toolbar_create_contact.*
import kotlinx.coroutines.Deferred
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class CreateContactFragment : MvpAppCompatFragment(), CreateContactView {

    private val mAdapter = ContactAdapter()

    @InjectPresenter
    lateinit var createContactPresenter: CreateContactPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_contact, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                CreateContactFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backContact.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }

        saveCreateContact.setOnClickListener {
            Toast.makeText(context, "Wow", Toast.LENGTH_SHORT)
            createContactPresenter.addContact()

            //Save Contact

        }

    }

    fun replaceFragment(fragment: ContactListFragment, istransition: Boolean) {
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

    override fun addInfo(data: List<Contact>) {

        mAdapter.setData(newContacts = data)

        activity!!.supportFragmentManager.popBackStack()
    }


}