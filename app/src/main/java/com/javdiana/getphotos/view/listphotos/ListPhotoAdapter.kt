package com.javdiana.getphotos.view.listphotos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.getphotos.R
import com.javdiana.getphotos.model.Photo

class ListPhotoAdapter() :
    ListAdapter<Photo, ListPhotoAdapter.ListPhotoHolder>(PostDiffCallback()) {
    private lateinit var context: Context

    override fun onBindViewHolder(holder: ListPhotoHolder, position: Int) {
        holder.bind(getItem(position), context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotoHolder {
        context = parent.context
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ListPhotoHolder(view)
    }

    class ListPhotoHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo, context: Context) {
            val imgPhoto: ImageView = itemView.findViewById(R.id.imgPhoto)
            Glide.with(context).load(photo.urls.small).into(imgPhoto)
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