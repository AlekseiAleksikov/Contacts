package com.example.kcontacts.adapters

import android.app.Application
import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kcontacts.R
import com.example.domain.models.Contact
import java.util.*
import kotlin.coroutines.coroutineContext
import com.example.kcontacts.adapters.ContactAdapter.ViewHolder as ViewHolder1

class ContactAdapter() : RecyclerView.Adapter<ViewHolder1>() {

    private val mContactList: MutableList<Contact> = LinkedList()
    private var listener: OnItemClickListener? = null

    fun setData(newContacts: List<Contact>) {
        mContactList.clear()
        mContactList.addAll(newContacts)
        notifyDataSetChanged()
    }

    fun insertData(newContact: Contact) {
        mContactList.add(newContact)
        notifyDataSetChanged()
    }

    fun deleteData(id: Int) {
        mContactList.removeAt(id)
        notifyDataSetChanged()

    }

    fun setOnClickListener(listener1: OnItemClickListener) {
        listener = listener1

    }

    class ViewHolder(itemView: View, val listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        private val txtSurname: TextView = itemView.findViewById(R.id.txtSurname)
        private val txtName: TextView = itemView.findViewById(R.id.txtName)
        private val txtPhone: TextView = itemView.findViewById(R.id.txtPhone)
        private val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
        private val menuContact: ImageView = itemView.findViewById(R.id.popupMenu)

        fun bind(model: Contact) {
            val idContact = model.id
            txtSurname.text = model.surname
            txtName.text = model.name
            txtPhone.text = model.phone

            menuContact.setOnClickListener {
                popupMenu(it, idContact)

            }

            itemView.setOnClickListener { listener?.onClicked(id = idContact) }

        }

        fun popupMenu(v: View, id: Int) {
            val popupMenu = PopupMenu(v.context, menuContact)
            popupMenu.inflate(R.menu.popup_menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.editContact -> {
                        listener?.editContact(id = id)
                        true
                    }
                    R.id.deleteContact -> {
                        listener?.deleteContact(id = id)
                        true
                    }
                    else -> true
                }
            }
            popupMenu.show()
        }


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_contact, viewGroup, false), listener = listener)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = mContactList[position])

    }

    override fun getItemCount(): Int {
        return mContactList.count()

    }

    interface OnItemClickListener {
        fun onClicked(id: Int)
        fun deleteContact(id: Int)
        fun editContact(id: Int)
    }


}