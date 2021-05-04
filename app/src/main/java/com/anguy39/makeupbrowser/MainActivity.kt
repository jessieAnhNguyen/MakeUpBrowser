package com.anguy39.makeupbrowser

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment

    private val prefs: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MakeUpBrowser)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        NavigationUI.setupActionBarWithNavController(this, navHostFragment.navController)

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            supportActionBar?.let {
                it.title = when (destination.id) {
                    R.id.settingsFragment -> getString(R.string.settings)
                    R.id.infoFragment -> getString(R.string.app_info_title)
                    //include ratings fragment here
                    else -> getString(R.string.app_name)
                }
            }
        }

    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.nav_host_fragment_main).navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_info_menu -> {
                navHostFragment.navController.navigate(R.id.action_welcomeFragment_to_infoFragment)
                true
            }
            R.id.settings_menu -> {
                navHostFragment.navController.navigate(R.id.action_welcomeFragment_to_settingsFragment)
                true
            }
            R.id.ratings_menu -> {
                navHostFragment.navController.navigate(R.id.action_welcomeFragment_to_ratingFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val CONFIRM_RATING = "confirm_rating_alert"
        const val CATEGORY_CHOICE = "category_choice"
        const val USERNAME = "username"
    }

}