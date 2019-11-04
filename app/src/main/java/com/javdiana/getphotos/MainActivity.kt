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

        val fragment1 = ListPhotosFragment()
        val fragment2 = SearchListPhotosFragment()
        val fm = supportFragmentManager
        var active: Fragment = fragment1

        fm.beginTransaction().add(R.id.fragment_container, fragment2, "SearchListPhotosFragment")
            .hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment1, "ListPhotosFragment")
            .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                list_photos -> {
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                }
                search_photos -> {
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}
