package com.example.cgr

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class FormManuelActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_manuel)

        val name = findViewById<EditText>(R.id.nameEditText)
        val firstName = findViewById<EditText>(R.id.firstNameEditText)
        val email = findViewById<EditText>(R.id.emailEditText)
        val address = findViewById<EditText>(R.id.addressEditText)
        val postalCode = findViewById<EditText>(R.id.postalCodeEditText)
        val city = findViewById<EditText>(R.id.cityEditText)
        val loyaltyCard = findViewById<EditText>(R.id.loyaltyCardEditText)
        val createAccountButton = findViewById<Button>(R.id.createAccountButton)

    }
}