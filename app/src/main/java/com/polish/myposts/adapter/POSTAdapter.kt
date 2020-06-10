package com.polish.myposts.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.polish.myposts.R
import com.polish.myposts.adapter.POSTAdapter.TextViewHolder.Companion.from
import com.polish.myposts.databinding.ListItemPostBinding
import com.polish.myposts.model.POST
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1



class POSTAdapter(val clickListener:OnClickListener): ListAdapter<POSTAdapter.DataItem, RecyclerView.ViewHolder >(DiffCallback){

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    class TextViewHolder(view: View):RecyclerView.ViewHolder(view){
        companion object {
            fun from(parent: ViewGroup):TextViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }

    }


    class POSTViewHolder(var binding:ListItemPostBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(post:POST, clickListener: OnClickListener){

            binding.post = post

            binding.clickListener = clickListener

            binding.executePendingBindings()

        }


        companion object {
            fun from(parent:ViewGroup):POSTViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemPostBinding.inflate(layoutInflater, parent, false)
                return POSTViewHolder(binding)
            }
        }


    }

    companion object DiffCallback:DiffUtil.ItemCallback<DataItem>(){
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
    @SuppressLint("DifficultEquals")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType){
           ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
           ITEM_VIEW_TYPE_ITEM -> POSTViewHolder.from(parent)
           else -> throw ClassCastException("Unknown viewType ${viewType}")
       }
    }
    // instead of using submitList(), I use this function to submit my header and list of postItem
    fun addHeaderAndSubmitList(list:List<POST>){
            Log.d("POSTAdapter", "what is inside list:$list")

            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.PostItem(it) }
            }


            submitList(items)



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is POSTViewHolder -> {
                val postItem = getItem(position) as DataItem.PostItem
                holder.bind(postItem.post, clickListener )
            }
        }
//        val post = getItem(position)
//
//        holder.bind(post, clickListener)

    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.PostItem -> ITEM_VIEW_TYPE_ITEM

        }
    }

    class OnClickListener(val clickListener:(post:POST) -> Unit){

        fun onClick(post: POST) = clickListener(post)

    }


    sealed class DataItem {

        abstract val id:Int

        data class PostItem(val post:POST):DataItem() {
            override val id: Int
                get() = post.id
        }

        object Header:DataItem() {
            override val id: Int
                get() = Int.MIN_VALUE
        }

    }



}