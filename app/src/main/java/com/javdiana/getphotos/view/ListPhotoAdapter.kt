package com.javdiana.getphotos.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.getphotos.R
import com.javdiana.getphotos.R.id.imgPhoto
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.view.ListPhotoAdapter.ListPhotoHolder


class ListPhotoAdapter(@LayoutRes val layout: Int) :
    PagedListAdapter<Photo, ListPhotoHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: ListPhotoHolder, position: Int) {
        holder.bind(getItem(position), layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotoHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(layout, parent, false)
        return ListPhotoHolder(view)
    }

    class ListPhotoHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo?, layout: Int) {
            val imgPhoto: ImageView = itemView.findViewById(imgPhoto)

            if (R.layout.search_item == layout) {
                Glide.with(itemView).load(photo!!.urls.small).into(imgPhoto)
            }else{
                Glide.with(itemView).load(photo!!.urls.thumb).into(imgPhoto)
            }
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