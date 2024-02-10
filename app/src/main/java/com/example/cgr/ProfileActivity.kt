package com.example.cgr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val name = findViewById<EditText>(R.id.nameEditText)
        val firstName = findViewById<EditText>(R.id.firstNameEditText)
        val email = findViewById<EditText>(R.id.emailEditText)
        val address = findViewById<EditText>(R.id.addressEditText)
        val postalCode = findViewById<EditText>(R.id.postalCodeEditText)
        val city = findViewById<EditText>(R.id.cityEditText)
        val loyaltyCard = findViewById<EditText>(R.id.loyaltyCardEditText)

        // Get the user data from SharedPreferences
        val sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)
        val userName = sharedPreferences.getString("name", "")
        val userFirstName = sharedPreferences.getString("firstName", "")
        val userEmail = sharedPreferences.getString("email", "")
        val userAddress = sharedPreferences.getString("address", "")
        val userPostalCode = sharedPreferences.getString("postalCode", "")
        val userCity = sharedPreferences.getString("city", "")
        val userLoyaltyCard = sharedPreferences.getString("loyaltyCard", "")


        name.setText(userName)
        firstName.setText(userFirstName)
        email.setText(userEmail)
        address.setText(userAddress)
        postalCode.setText(userPostalCode)
        city.setText(userCity)
        loyaltyCard.setText(userLoyaltyCard)
    }
}