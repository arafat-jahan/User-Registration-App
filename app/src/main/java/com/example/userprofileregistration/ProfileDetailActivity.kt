package com.example.userprofileregistration

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileDetailActivity : AppCompatActivity() {
    private lateinit var userProfile: UserProfile

    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var dobTextView: TextView
    private lateinit var districtTextView: TextView
    private lateinit var mobileTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_detail)
        userProfile = intent.getSerializableExtra("USER_PROFILE") as UserProfile

        nameTextView = findViewById(R.id.nameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        dobTextView = findViewById(R.id.dobTextView)
        districtTextView = findViewById(R.id.districtTextView)
        mobileTextView = findViewById(R.id.mobileTextView)

        populateFields()
    }

    private fun populateFields() {
        nameTextView.text = userProfile.name
        emailTextView.text = userProfile.email
        dobTextView.text = userProfile.dob
        districtTextView.text = userProfile.district
        mobileTextView.text = userProfile.mobile
    }
}