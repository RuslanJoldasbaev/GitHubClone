package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.github.R
import com.example.github.data.models.GetUserProfileInfo
import com.example.github.data.models.RepositoryItem
import com.example.github.databinding.ItemRepositoryBinding

class RepositoryAdapter :
    ListAdapter<RepositoryItem, RepositoryAdapter.RepositoryViewHolder>(diffCallBack) {

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(adapterPosition)

            binding.apply {

                textNameItem.text = d.name
                textUsernameItem.text = d.owner.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            ItemRepositoryBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_repository, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
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