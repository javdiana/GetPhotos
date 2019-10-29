package com.javdiana.getphotos.view.listphotos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.getphotos.R
import com.javdiana.getphotos.R.id.imgPhoto
import com.javdiana.getphotos.model.Photo

class ListPhotoAdapter() : ListAdapter<Photo, ListPhotoAdapter.ListPhotoHolder>(PostDiffCallback) {
    override fun onBindViewHolder(holder: ListPhotoHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotoHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ListPhotoHolder(view, parent.context)
    }

    class ListPhotoHolder(private val itemView: View, context: Context) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo) {
            Glide.with(this.context).load(photo.urls.self).into(imgPhoto)
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