package com.javdiana.getphotos.view.searchphotos

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.javdiana.getphotos.R.layout
import kotlinx.android.synthetic.main.fragment_list_photos.*


class SearchPhotosFragment : Fragment() {
    private lateinit var viewModel: SearchListPhotosViewModel
    private val adapter: SearchListPhotoAdapter = SearchListPhotoAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout.fragment_list_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initPhotos()

    }

    private fun initViewModel() {
        viewModel = SearchListPhotosViewModel()
        viewModel.getPhotos()
        viewModel.query = "car"

        viewModel.searchPhotos.observe(this, Observer { list ->
            //adapter.submitList(list.toMutableList())
        })
    }

    private fun initPhotos() {
        rvPhotos.layoutManager = LinearLayoutManager(activity)
        rvPhotos.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.javdiana.getphotos.R.menu.options_menu, menu)
        val searchItem = menu.findItem(com.javdiana.getphotos.R.id.search)
        //val searchView =
        // SearchView((MainActivity)activity.getSupportActionBar().getThemedContext())
//        MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM)
//        //searchView.queryHint = "Search photos"
//        MenuItemCompat.setActionView(searchItem, searchView);
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return if (newText?.length!! >= 3) {
//                    viewModel.query = newText
//                    viewModel.getPhotos()
//                    true
//                } else {
//                    false
//                }
//
//            }
//
//        })
    }
}