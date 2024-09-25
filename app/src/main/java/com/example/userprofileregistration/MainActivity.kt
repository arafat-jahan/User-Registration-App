package com.example.userprofileregistration

import android.content.Intent
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var listButton: Button
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)

        // Set up the ActionBarDrawerToggle to handle drawer open/close
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Show the toggle in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize the button for Profile List and set up the click event
        listButton = findViewById(R.id.profileListBtn)
        listButton.setOnClickListener {
            openLoadingActivity()
        }

        // Set the navigation item selected listener for the NavigationView
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileMenuItem -> {
                    // Load ProfileFragment when "Profile" is selected
                    loadFragment(ProfileFragment())
                    drawerLayout.closeDrawers() // Close the drawer
                    true
                }
                R.id.profileListMenuItem -> {
                    // Open ProfileListActivity via LoadingActivity
                    openLoadingActivity()
                    drawerLayout.closeDrawers() // Close the drawer
                    true
                }
                else -> false
            }
        }
    }

    // Open the loading activity and pass the target activity (ProfileListActivity)
    private fun openLoadingActivity() {
        val intent = Intent(this, LoadingActivity::class.java)
        intent.putExtra("TARGET_ACTIVITY", "com.example.userprofileregistration.ProfileListActivity")
        startActivity(intent)
        // Do not finish MainActivity here to keep it in the back stack
    }

    // Load a fragment into the FrameLayout (fragment_container)
    private fun loadFragment(fragment: ProfileFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        // If the drawer is open, close it first
        if (drawerLayout.isDrawerOpen(androidx.core.view.GravityCompat.START)) {
            drawerLayout.closeDrawer(androidx.core.view.GravityCompat.START)
        } else {
            // Move the app to the background instead of closing it
            moveTaskToBack(true)
        }
    }

    // Handle the ActionBarDrawerToggle clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
