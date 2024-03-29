package com.javdiana.getphotos.view.listphotos

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.javdiana.getphotos.R
import com.javdiana.getphotos.R.id.update
import com.javdiana.getphotos.view.ListPhotoAdapter
import kotlinx.android.synthetic.main.fragment_list_photos.*

class ListPhotosFragment : Fragment() {
    private lateinit var viewModel: ListPhotosViewModel
    private lateinit var adapter: ListPhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_photos, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initPhotos()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ListPhotosViewModel::class.java)


        viewModel.photos.observe(this, Observer {
            if (it.isEmpty()) {
                tvNoItems.visibility = View.VISIBLE
            } else {
                tvNoItems.visibility = View.GONE
            }
            adapter.submitList(it)
        })
    }

    private fun initPhotos() {
        rvPhotos.layoutManager = GridLayoutManager(activity, 3)
        adapter = ListPhotoAdapter(R.layout.image_item)
        rvPhotos.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_photos, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == update) {
            viewModel.loadPhotos()
        }
        return super.onOptionsItemSelected(item)
    }
}
