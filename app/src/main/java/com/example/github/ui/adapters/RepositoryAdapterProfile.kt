package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.github.R
import com.example.github.data.models.RepositoryItem
import com.example.github.databinding.ItemProfileBinding
import com.example.github.databinding.ItemRepositoryBinding

class RepositoryAdapterProfile :
    ListAdapter<RepositoryItem, RepositoryAdapterProfile.RepositoryProfileViewHolder>(diffCallBack) {

    inner class RepositoryProfileViewHolder(private val binding: ItemProfileBinding) :
        ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(adapterPosition)

            binding.apply {

                Glide.with(itemProfileImg)
                    .load(d.owner.avatar_url)
                    .into(itemProfileImg)


                itemTextProject.text = d.name
                itemTextUsername.text = d.owner.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryProfileViewHolder {
        return RepositoryProfileViewHolder(
            ItemProfileBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_profile, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryProfileViewHolder, position: Int) {
        holder.bind()
    }

    private object diffCallBack : DiffUtil.ItemCallback<RepositoryItem>() {
        override fun areItemsTheSame(
            oldItem: RepositoryItem,
            newItem: RepositoryItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RepositoryItem,
            newItem: RepositoryItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}