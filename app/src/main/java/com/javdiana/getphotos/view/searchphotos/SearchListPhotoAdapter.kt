package com.javdiana.getphotos.view.searchphotos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.getphotos.R
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.view.searchphotos.SearchListPhotoAdapter.SearchListPhotoHolder

class SearchListPhotoAdapter(private val retry: () -> Unit) :
    PagedListAdapter<Photo, SearchListPhotoHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: SearchListPhotoHolder, position: Int) {
        val photo = getItem(position)
        println(photo)
        holder.bind(photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListPhotoHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchListPhotoHolder(view)
    }

    class SearchListPhotoHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo?) {
            val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto)
            Glide.with(itemView).load(photo?.urls?.small).into(imgPhoto)
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }

}
