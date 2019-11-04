package com.javdiana.getphotos.view.listphotos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javdiana.getphotos.R.id.imgPhoto
import com.javdiana.getphotos.model.Photo
import com.javdiana.getphotos.view.listphotos.ListPhotoAdapter.ListPhotoHolder


class ListPhotoAdapter(private val retry: () -> Unit) :
    PagedListAdapter<Photo, ListPhotoHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: ListPhotoHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPhotoHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(com.javdiana.getphotos.R.layout.image_item, parent, false)
        return ListPhotoHolder(view)
    }

    class ListPhotoHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(photo: Photo?) {
//            val params = itemView.layoutParams
//            params.height = params.width / 2
//            itemView.layoutParams = params
            val imgPhoto: ImageView = itemView.findViewById(imgPhoto)
            Glide.with(itemView).load(photo!!.urls.thumb).into(imgPhoto)
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