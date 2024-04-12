package com.caiosilva.projetomarvel2tdspr

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caiosilva.projetomarvel2tdspr.databinding.ComicListItemBinding
import com.squareup.picasso.Picasso

class ComicsAdapter :
//    ListAdapter<ComicBookData, ComicsAdapter.ComicsViewHolder>(DiffCallBack()) {
    ListAdapter<Posts, ComicsAdapter.ComicsViewHolder>(DiffCallBack()) {

    var onClick: (ComicBookData) -> Unit = {}
    var onPostClick: (Posts) -> Unit = {}

    inner class ComicsViewHolder(val binding: ComicListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        fun bind(comicBookData: ComicBookData) {
        fun bind(comicBookData: Posts) {
            binding.textViewTitle.text = comicBookData.title
            binding.textViewDescription.text = comicBookData.body
            binding.textViewPageCount.text = comicBookData.userId.toString()

//          binding.imageViewCover.setImageFromUrl(comicBookData.imageUrl)
//
//          Implementação normal do Picasso para usar URLs em ImageViews
//          Picasso.get().load(comicBookData.imageUrl).into(binding.imageViewCover)

            binding.root.setOnClickListener {
                onPostClick(comicBookData)
//                onClick(comicBookData)
            }
        }
    }

//    class DiffCallBack : DiffUtil.ItemCallback<ComicBookData>() {
    class DiffCallBack : DiffUtil.ItemCallback<Posts>() {

//            override fun areItemsTheSame(oldItem: ComicBookData, newItem: ComicBookData): Boolean {
        override fun areItemsTheSame(oldItem: Posts, newItem: Posts): Boolean {

            return oldItem.id == newItem.id
        }

//        override fun areContentsTheSame(oldItem: ComicBookData, newItem: ComicBookData): Boolean {
        override fun areContentsTheSame(oldItem: Posts, newItem: Posts): Boolean {

            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ComicListItemBinding =
            ComicListItemBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ComicsViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(
            getItem(position)
        )
    }
}