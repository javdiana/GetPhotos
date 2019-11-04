package com.javdiana.getphotos.view.listphotos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.javdiana.getphotos.NetworkManager
import com.javdiana.getphotos.R
import kotlinx.android.synthetic.main.fragment_list_photos.*

class ListPhotosFragment : Fragment() {
    private lateinit var viewModel: ListPhotosViewModel
    private lateinit var adapter: ListPhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initPhotos()
    }

    private fun initViewModel() {
        viewModel = ListPhotosViewModel()
    }

    private fun initPhotos() {
        rvPhotos.layoutManager = GridLayoutManager(activity, 3)
        if (NetworkManager.isNetworkAvailable(context)) {
            adapter = ListPhotoAdapter { viewModel.retry() }
            rvPhotos.adapter = adapter
            viewModel.photos.observe(this, Observer {
                if (it == null) {
                    tvNoItems.visibility = View.VISIBLE
                } else {
                    adapter.submitList(it)
                    tvNoItems.visibility = View.GONE
                }
            })
        } else {
            Toast.makeText(context, "You have not Internet", Toast.LENGTH_SHORT).show()
        }
    }
}
