package com.javdiana.getphotos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.javdiana.getphotos.R.id.list_photos
import com.javdiana.getphotos.R.id.search_photos
import com.javdiana.getphotos.view.listphotos.ListPhotosFragment
import com.javdiana.getphotos.view.searchphotos.SearchListPhotosFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentListPhotos = ListPhotosFragment()
        val fragmentSearchPhotos = SearchListPhotosFragment()
        val fm = supportFragmentManager
        var active: Fragment = fragmentListPhotos

        fm.beginTransaction().add(R.id.fragment_container, fragmentSearchPhotos, "SearchListPhotosFragment")
            .hide(fragmentSearchPhotos).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragmentListPhotos, "ListPhotosFragment")
            .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                list_photos -> {
                    fm.beginTransaction().hide(active).show(fragmentListPhotos).commit();
                    active = fragmentListPhotos;
                }
                search_photos -> {
                    fm.beginTransaction().hide(active).show(fragmentSearchPhotos).commit();
                    active = fragmentSearchPhotos;
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
