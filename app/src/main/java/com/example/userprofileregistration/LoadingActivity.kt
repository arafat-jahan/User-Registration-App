package com.example.userprofileregistration

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class LoadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // Simulate loading or some processing time
        Handler(Looper.getMainLooper()).postDelayed({
            // Retrieve the target activity and user profile
            val targetActivityName = intent.getStringExtra("TARGET_ACTIVITY")
            val userProfile = intent.getSerializableExtra("USER_PROFILE") as UserProfile?

            // Navigate to the target activity
            val targetIntent = Intent()
            targetIntent.setClassName(this, targetActivityName ?: return@postDelayed)
            userProfile?.let {
                targetIntent.putExtra("USER_PROFILE", it)
            }
            startActivity(targetIntent)
            finish() // Finish this activity so the user can't return to it
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}
