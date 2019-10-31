package com.javdiana.getphotos.view.listphotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.javdiana.getphotos.R

class ListPhotosFragment : Fragment() {
    private lateinit var viewModel: ListPhotosViewModel
    private val adapter = ListPhotoAdapter()
    private lateinit var rvPhotos: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_photos, container, false)

        rvPhotos = view.findViewById(R.id.rvPhotos)

        initViewModel()
        initPhotos()

        return view
    }

    private fun initViewModel() {
        viewModel = ListPhotosViewModel()
        viewModel.getPhotos()

        viewModel.photos.observe(this, Observer { list ->
            adapter.submitList(list.toMutableList())
        })
    }

    private fun initPhotos() {
        rvPhotos.layoutManager = GridLayoutManager(activity, 3)
        rvPhotos.adapter = adapter
    }
}
