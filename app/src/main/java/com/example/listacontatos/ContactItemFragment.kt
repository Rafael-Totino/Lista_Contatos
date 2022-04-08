package com.example.listacontatos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.listacontatos.databinding.FragmentContactItemBinding
import com.example.listacontatos.databinding.FragmentContactListBinding


class ContactItemFragment(private val contact: Contact) : Fragment() {
    private lateinit var binding: FragmentContactItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactListItemFullname.text = contact.name
        binding.contactListItemPhoneValue.text = contact.phone
        binding.contactListItemEmailValue.text = contact.email
        binding.contactListItemAddressValue.text = contact.address
    }
}