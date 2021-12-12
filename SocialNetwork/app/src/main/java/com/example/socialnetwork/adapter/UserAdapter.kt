package com.example.socialnetwork.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialnetwork.R
import com.example.socialnetwork.model.User

interface OnItemClick {
    fun onClick(userId: Int)
}

class UserAdapter(private val onItemClick: OnItemClick) :
    ListAdapter<User, UserAdapter.ProfileHolder>(object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean = oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_profile, parent, false)
        return ProfileHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView.context)
    }

    inner class ProfileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.userName)
        private val onlineStatusTextView: TextView = itemView.findViewById(R.id.onlineStatus)
        private val profileImageView: ImageView = itemView.findViewById(R.id.profileImage)

        fun bind(item: User, context: Context) {
            nameTextView.text = item.name
            onlineStatusTextView.text = item.onlineStatus
            Glide.with(context)
                .load(item.photo)
                .error(R.drawable.ic_generic_avatar)
                .into(profileImageView)
            itemView.setOnClickListener {
                onItemClick.onClick(item.id)
            }
        }
    }
}