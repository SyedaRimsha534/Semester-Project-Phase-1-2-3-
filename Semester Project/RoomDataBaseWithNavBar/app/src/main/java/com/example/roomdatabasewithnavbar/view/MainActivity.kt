package com.example.roomdatabasewithnavbar.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.roomdatabasewithnavbar.R
import com.example.roomdatabasewithnavbar.fragments.add.FragmentAdd
import com.example.roomdatabasewithnavbar.fragments.favourite.FragmentFavourites
import com.example.roomdatabasewithnavbar.fragments.list.FragmentList
import com.example.roomdatabasewithnavbar.fragments.retrofitFruit.FragmentFruit
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inflateFragment(FragmentList.newInstance())

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_bar)

        bottomBar.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.nav_list -> {
                    inflateFragment(FragmentList.newInstance())
                }

                R.id.nav_add -> {
                    inflateFragment(FragmentAdd.newInstance())
                }

                R.id.nav_favourites -> {
                    inflateFragment(FragmentFavourites.newInstance())
                }

                R.id.nav_retrofit -> {
                    inflateFragment(FragmentFruit.newInstance())
                }
            }



            true
        }

    }

    private fun inflateFragment(newInstance: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()

    }
}