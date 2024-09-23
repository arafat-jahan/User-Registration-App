package com.example.userprofileregistration

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel: UserProfileViewModel
    private lateinit var progressDialog: ProgressDialog // ProgressDialog variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        val nameEditText = findViewById<EditText>(R.id.profileNameEt)
        val emailEditText = findViewById<EditText>(R.id.profileEmailEt)
        val dobEditText = findViewById<EditText>(R.id.profileDOBEt)
        val districtEditText = findViewById<EditText>(R.id.profileDistrictEt)
        val mobileEditText = findViewById<EditText>(R.id.profilemobileEt)

        // Initialize ProgressDialog
        progressDialog = ProgressDialog(this).apply {
            setMessage("Adding Profile...")
            setCancelable(false) // Prevents closing while loading
        }

        findViewById<Button>(R.id.addBtn).setOnClickListener {
            if (validateInputs(nameEditText, emailEditText, dobEditText, districtEditText, mobileEditText)) {
                showLoading() // Show loading before adding profile

                val name = nameEditText.text.toString().trim()
                val email = emailEditText.text.toString().trim()
                val dob = dobEditText.text.toString().trim()
                val district = districtEditText.text.toString().trim()
                val mobile = mobileEditText.text.toString().trim()

                val userProfile = UserProfile(name = name, email = email, dob = dob, district = district, mobile = mobile)
                profileViewModel.insertUserProfile(userProfile)

                // Simulate loading and delay for 2 seconds
                nameEditText.postDelayed({
                    hideLoading() // Hide loading after a delay
                    Toast.makeText(this, "Profile added successfully", Toast.LENGTH_SHORT).show()
                    finish() // Close the activity
                }, 2000) // 2 seconds delay
            }
        }
    }

    private fun validateInputs(
        nameEditText: EditText,
        emailEditText: EditText,
        dobEditText: EditText,
        districtEditText: EditText,
        mobileEditText: EditText
    ): Boolean {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val dob = dobEditText.text.toString().trim()
        val district = districtEditText.text.toString().trim()
        val mobile = mobileEditText.text.toString().trim()

        return when {
            name.isEmpty() -> {
                nameEditText.error = "Please enter your name"
                nameEditText.requestFocus()
                false
            }
            email.isEmpty() -> {
                emailEditText.error = "Please enter your email"
                emailEditText.requestFocus()
                false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                emailEditText.error = "Please enter a valid email"
                emailEditText.requestFocus()
                false
            }
            dob.isEmpty() -> {
                dobEditText.error = "Please enter your date of birth"
                dobEditText.requestFocus()
                false
            }
            district.isEmpty() -> {
                districtEditText.error = "Please enter your district"
                districtEditText.requestFocus()
                false
            }
            mobile.isEmpty() -> {
                mobileEditText.error = "Please enter your mobile number"
                mobileEditText.requestFocus()
                false
            }
            else -> true // All inputs are valid
        }
    }

    private fun showLoading() {
        // Show the progress dialog
        progressDialog.show()
    }

    private fun hideLoading() {
        // Dismiss the progress dialog
        progressDialog.dismiss()
    }
}
