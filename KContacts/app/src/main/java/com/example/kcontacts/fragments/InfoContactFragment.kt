package com.example.kcontacts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kcontacts.R
import kotlinx.android.synthetic.main.toolbar_create_contact.*
import kotlinx.android.synthetic.main.toolbar_info_contact.*
import moxy.MvpAppCompatFragment

class InfoContactFragment : MvpAppCompatFragment() {

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
        return inflater.inflate(R.layout.fragment_info_contact, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                InfoContactFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backInfoContact.setOnClickListener {
            activity!!.supportFragmentManager.popBackStack()
        }

        editInfoContact.setOnClickListener {
            replaceFragment(CreateContactFragment.newInstance(), false)
        }
    }

    fun replaceFragment(fragment: CreateContactFragment, istransition: Boolean) {
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

}