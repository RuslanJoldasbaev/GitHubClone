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
import com.example.github.databinding.ItemProfileRepoBinding
import com.example.github.databinding.ItemRepositoryBinding

class ProfileRepositoryAdapter :
    ListAdapter<RepositoryItem, ProfileRepositoryAdapter.RepoProfileViewHolder>(diffCallBack) {

    inner class RepoProfileViewHolder(private val binding: ItemProfileRepoBinding) :
        ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(adapterPosition)

            binding.apply {

                itemTextProjectProf.text = d.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoProfileViewHolder {
        return RepoProfileViewHolder(
            ItemProfileRepoBinding.bind(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.item_profile_repo, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: RepoProfileViewHolder, position: Int) {
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