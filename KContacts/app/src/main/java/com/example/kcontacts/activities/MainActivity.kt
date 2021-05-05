package com.example.kcontacts.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kcontacts.R
import com.example.kcontacts.adapters.ContactAdapter
import com.example.domain.models.Contact
import com.example.kcontacts.fragments.ContactListFragment
import com.example.kcontacts.presenters.ContactListPresenter
import com.example.kcontacts.views.ContactListView
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity() {
  /*  private val mAdapter = ContactAdapter()

    @InjectPresenter
    lateinit var contactListPresenter: ContactListPresenter */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(ContactListFragment.newInstance(), true)

    }

    fun replaceFragment (fragment: Fragment, istransition: Boolean) {
        val fragmentTransition = supportFragmentManager.beginTransaction()

        if (istransition) {
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
        }
        fragmentTransition.replace(R.id.frame_layout, fragment).addToBackStack(fragment.javaClass.simpleName)
        fragmentTransition.commit()

    }

}