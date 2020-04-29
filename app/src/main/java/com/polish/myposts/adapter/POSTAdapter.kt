package com.polish.myposts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polish.myposts.databinding.ListItemPostBinding
import com.polish.myposts.model.POST

class POSTAdapter(): ListAdapter<POST, POSTAdapter.POSTViewHolder >(DiffCallback){

    class POSTViewHolder(var binding:ListItemPostBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(post:POST){

            binding.post = post

            binding.executePendingBindings()

        }

    }

    companion object DiffCallback:DiffUtil.ItemCallback<POST>(){
        override fun areItemsTheSame(oldItem: POST, newItem: POST): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: POST, newItem: POST): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): POSTViewHolder {
       return POSTViewHolder(ListItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: POSTViewHolder, position: Int) {
        val post = getItem(position)

        holder.bind(post)

    }

}