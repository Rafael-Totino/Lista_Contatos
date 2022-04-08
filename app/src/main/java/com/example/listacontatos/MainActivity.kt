package com.example.listacontatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.beust.klaxon.Klaxon
import com.example.listacontatos.databinding.ActivityMainBinding
import com.example.listacontatos.databinding.ContactListItemBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private data class Contact(
        var name: String,
        var email: String,
        var phone: String,
        var address: String
    )

    private class ContactAdapter(val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

       private class ContactHolder(itemBinding: ContactListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

           val name = itemBinding.contactListItemFullname
           val email = itemBinding.contactListItemEmailValue
           val phone = itemBinding.contactListItemPhoneValue
           val address = itemBinding.contactListItemAddressValue
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ContactAdapter.ContactHolder {
            val itemBinding = ContactListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ContactHolder(itemBinding)
        }

        override fun onBindViewHolder(holder: ContactAdapter.ContactHolder, position: Int) {
            val contact = contacts[position]

            holder.name.text = contact.name
            holder.email.text = contact.email
            holder.phone.text = contact.phone
            holder.address.text = contact.address
        }

        override fun getItemCount(): Int = contacts.size

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        resources.assets.open("contacts.json").use {
            binding.recyclerviewContactList.apply {
                adapter = ContactAdapter(Klaxon().parseArray(it) ?: emptyList())
                addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
            }
        }


    }
}