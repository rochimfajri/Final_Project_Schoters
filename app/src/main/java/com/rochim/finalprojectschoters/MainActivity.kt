package com.rochim.finalprojectschoters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.rochim.finalprojectschoters.databinding.ActivityMainBinding
import com.rochim.finalprojectschoters.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var frag: String
    private lateinit var hint: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        frag = intent.getStringExtra("frag").toString()
        hint="Search news"

        when(frag){
            "home" -> {
                replaceFragment(HomeFragment())
                binding.bottomNavigationView.selectedItemId = R.id.home
            }
            "sport" -> {
                replaceFragment(SportFragment())
                binding.bottomNavigationView.selectedItemId = R.id.sport
            }
            "health" -> {
                replaceFragment(HealthFragment())
                binding.bottomNavigationView.selectedItemId = R.id.health
            }
            "science" -> {
                replaceFragment(ScienceFragment())
                binding.bottomNavigationView.selectedItemId = R.id.science
            }
            "search" -> {
                hint = intent.getStringExtra("hint").toString()
                replaceFragment(SearchFragment())
                binding.bottomNavigationView.selectedItemId = R.id.search
            }
            else ->{
                replaceFragment(HomeFragment())
                binding.bottomNavigationView.selectedItemId = R.id.home
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(HomeFragment())
                R.id.sport -> replaceFragment(SportFragment())
                R.id.health -> replaceFragment(HealthFragment())
                R.id.science -> replaceFragment(ScienceFragment())
                R.id.search -> replaceFragment(SearchFragment())

                else ->{

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        frag = ""
        intent.putExtra("hint",hint)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}