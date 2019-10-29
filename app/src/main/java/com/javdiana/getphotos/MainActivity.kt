package com.javdiana.getphotos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.javdiana.getphotos.R.id.list_photos
import com.javdiana.getphotos.R.id.search_photos
import com.javdiana.getphotos.view.listphotos.ListPhotosFragment
import com.javdiana.getphotos.view.searchphotos.SearchPhotosFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListPhotosFragment())
            .commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            var fragment: Fragment? = null
            when (it.itemId) {
                list_photos -> {
                    fragment = ListPhotosFragment()
                }
                search_photos -> {
                    fragment = SearchPhotosFragment()
                }
            }
            if (fragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
            }

            return@setOnNavigationItemSelectedListener true
        }
    }
}
