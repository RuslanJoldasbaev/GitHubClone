package com.example.github.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.github.R
import com.example.github.data.models.GetUserProfileInfo
import com.example.github.databinding.ItemProfileBinding

class RepositoryAdapterProfile :
    ListAdapter<GetUserProfileInfo, RepositoryAdapterProfile.RepositoryProfileViewHolder>(
        diffCallBack
    ) {

    inner class RepositoryProfileViewHolder(private val binding: ItemProfileBinding) :
        ViewHolder(binding.root) {
        fun bind() {

            val d = getItem(adapterPosition)

            binding.apply {
                itemTextUsername.text = d.login
                itemTextProject.text = d.name

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

    private object diffCallBack : DiffUtil.ItemCallback<GetUserProfileInfo>() {
        override fun areItemsTheSame(
            oldItem: GetUserProfileInfo,
            newItem: GetUserProfileInfo
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetUserProfileInfo,
            newItem: GetUserProfileInfo
        ): Boolean {
            return oldItem == newItem
        }

    }
}