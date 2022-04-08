package com.example.listacontatos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.listacontatos.databinding.ContactListItemBinding
import com.example.listacontatos.databinding.FragmentContactListBinding


class ContactListFragment(private val contacts: List<Contact>) : Fragment() {
    private lateinit var binding: FragmentContactListBinding

    companion object {
        const val itemClickedKey = "itemClickedKey"
        const val itemClickedPosition = "itemClickedPosition"
    }

    private inner class ContactAdapter(val contacts: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

        private inner class ContactHolder(itemBinding: ContactListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

            val name = itemBinding.contactListItemFullname

            init {
                itemBinding.root.setOnClickListener {
                    setFragmentResult(itemClickedKey, bundleOf(itemClickedPosition to bindingAdapterPosition))
                    bindingAdapterPosition
                }
            }
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

        }

        override fun getItemCount(): Int = contacts.size

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.recyclerviewContactList.apply {
            adapter = ContactAdapter(contacts)
            addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL))
        }
    }
}
