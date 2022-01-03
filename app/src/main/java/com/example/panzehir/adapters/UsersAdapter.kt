package com.example.panzehir.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.panzehir.databinding.ItemcontaineruserBinding
import com.example.panzehir.listeners.UsersListener
import com.example.panzehir.models.User

class UsersAdapter(private var users: ArrayList<User>, private var usersListener: UsersListener): RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(val itemBinding: ItemcontaineruserBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemcontaineruserBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder){
            itemBinding.textFirstChar.text = users[position].firstNamePatient.substring(0,1)
            itemBinding.textUserName.text = String.format("%s %s",users[position].firstNamePatient,users[position].lastNamePatient)
            itemBinding.textEmail.text = users[position].email
            itemBinding.imageAudioMeeting.setOnClickListener { usersListener.initiateAudioMeeting(users[position]) }
            itemBinding.imageVideoMeeting.setOnClickListener { usersListener.initiateVideoMeeting(users[position]) }

        }
    }




}