package com.caiosilva.projetomarvel2tdspr

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caiosilva.projetomarvel2tdspr.databinding.ComicListItemBinding

class ComicsAdapter : ListAdapter<ComicBookData, ComicsAdapter.ComicsViewHolder>(DiffCallBack()) {

    inner class ComicsViewHolder(val binding: ComicListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comicBookData: ComicBookData) {
            binding.textViewTitle.text = comicBookData.title
            binding.textViewDescription.text = comicBookData.description
            binding.textViewPageCount.text = comicBookData.pageCount.toString()
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<ComicBookData>() {
        override fun areItemsTheSame(oldItem: ComicBookData, newItem: ComicBookData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ComicBookData, newItem: ComicBookData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ComicListItemBinding = ComicListItemBinding.inflate(layoutInflater)
        val viewHolder = ComicsViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(
            getItem(position)
        )
    }
}