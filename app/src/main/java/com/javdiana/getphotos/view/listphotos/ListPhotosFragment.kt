package com.javdiana.getphotos.view.listphotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.javdiana.getphotos.R
import com.javdiana.getphotos.api.PhotosRepository
import io.reactivex.Observer
import kotlinx.android.synthetic.main.fragment_list_photos.*

class ListPhotosFragment : Fragment() {
    private lateinit var viewModel: ListPhotosViewModel
    private val adapter = ListPhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_list_photos, container, false)

        initViewModel()
        initPhotos()

        return view
    }

    private fun initViewModel() {
        viewModel = ListPhotosViewModel(PhotosRepository())
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
