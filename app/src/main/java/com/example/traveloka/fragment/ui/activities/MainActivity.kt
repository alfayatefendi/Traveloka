package com.example.traveloka.fragment.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.traveloka.BookingFragment
import com.example.traveloka.BookmarkFragment
import com.example.traveloka.ExploreFragment
import com.example.traveloka.ProfileFragment
import com.example.traveloka.R
import com.example.traveloka.databinding.ActivityMainBinding
import com.example.traveloka.fragment.ui.home.HomeFragment
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val toolbar = binding.layoutToolbar.toolbar as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
        setContentView(binding.root)
        // val navHostFragment =
        //   supportFragmentManager.findFragmentById(R.id.fragment_container_main) as NavHostFragment
        //navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavMain
        val radius = resources.getDimension(R.dimen.bottom_nav_corner)
        val bottomNavigationBackground = bottomNavigationView.background as MaterialShapeDrawable
        bottomNavigationBackground.shapeAppearanceModel = bottomNavigationBackground.shapeAppearanceModel.toBuilder().apply {
            setAllCorners(CornerFamily.ROUNDED, radius)
        }.build()

        replaceFragment(HomeFragment())

        binding.bottomNavMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment())
                R.id.nav_explore -> replaceFragment(ExploreFragment())
                R.id.nav_booking -> replaceFragment(BookingFragment())
                R.id.nav_bookmark -> replaceFragment(BookmarkFragment())
                R.id.nav_profile -> replaceFragment(ProfileFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container_main, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.action_bar_main_menu, menu)
        return true
    }
}