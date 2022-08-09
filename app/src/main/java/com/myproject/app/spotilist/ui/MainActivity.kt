package com.myproject.app.spotilist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityMainBinding
import com.myproject.app.spotilist.ui.favorite.FavoriteFragment
import com.myproject.app.spotilist.ui.home.HomeFragment
import com.myproject.app.spotilist.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.setItemSelected(R.id.nav_home, true)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, HomeFragment())
            .commit()

        navBottom()
    }

    private fun navBottom() {
        binding.navView.setOnItemSelectedListener {
            var fragment: Fragment? = null
            fragment = when (it) {
                R.id.nav_search -> SearchFragment()
                R.id.nav_favorite -> FavoriteFragment()
                else -> HomeFragment()
            }

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
    }
}