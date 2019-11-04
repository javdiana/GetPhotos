package com.javdiana.getphotos.view.searchphotos

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.getphotos.R
import com.javdiana.getphotos.R.layout
import kotlinx.android.synthetic.main.fragment_list_photos.*


class SearchListPhotosFragment : Fragment() {
    private lateinit var viewModel: SearchListPhotosViewModel
    private lateinit var adapter: SearchListPhotoAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_list_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initViewModel()
        initPhotos()
    }

    private fun initViewModel() {
        viewModel = SearchListPhotosViewModel()

        viewModel.searchPhotos.observe(
            this, Observer {
                if (it.isEmpty()) {
                    tvNoItems.visibility = View.VISIBLE
                } else {
                    adapter.submitList(it)
                    tvNoItems.visibility = View.GONE
                }
            })
    }

    private fun initPhotos() {
        rvPhotos.layoutManager = LinearLayoutManager(activity)
        adapter = SearchListPhotoAdapter { viewModel.retry() }
        rvPhotos.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText?.length!! >= 3) {
                    viewModel.search(newText)
                    true
                } else {
                    false
                }

            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}