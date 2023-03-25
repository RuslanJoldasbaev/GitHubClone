package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.data.models.UserItems
import com.example.github.databinding.ItemUserBinding

class UsernameSearchAdapter :
    ListAdapter<UserItems, UsernameSearchAdapter.UsernameViewHolder>(diffCallBack) {

    inner class UsernameViewHolder(private val binding: ItemUserBinding) :
        ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(adapterPosition)

            binding.apply {
                Glide.with(profileImgSearch)
                    .load(d.avatar_url)
                    .into(profileImgSearch)

                textUsernameSearch.text = d.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsernameViewHolder {
        return UsernameViewHolder(
            ItemUserBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_user, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: UsernameViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallBack : DiffUtil.ItemCallback<UserItems>() {
        override fun areItemsTheSame(
            oldItem: UserItems,
            newItem: UserItems
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserItems,
            newItem: UserItems
        ): Boolean {
            return oldItem == newItem
        }

    }
}