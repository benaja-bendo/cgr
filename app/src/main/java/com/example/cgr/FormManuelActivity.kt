package com.example.cgr

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormManuelActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_manuel)

        val createAccountButton = findViewById<Button>(R.id.createAccountButton)

        createAccountButton.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val name = findViewById<EditText>(R.id.nameEditText)
        val firstName = findViewById<EditText>(R.id.firstNameEditText)
        val email = findViewById<EditText>(R.id.emailEditText)
        val address = findViewById<EditText>(R.id.addressEditText)
        val postalCode = findViewById<EditText>(R.id.postalCodeEditText)
        val city = findViewById<EditText>(R.id.cityEditText)
        val loyaltyCard = findViewById<EditText>(R.id.loyaltyCardEditText)

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("name", name.text.toString())
        editor.putString("firstName", firstName.text.toString())
        editor.putString("email", email.text.toString())
        editor.putString("address", address.text.toString())
        editor.putString("postalCode", postalCode.text.toString())
        editor.putString("city", city.text.toString())
        editor.putString("loyaltyCard", loyaltyCard.text.toString())
        editor.apply()

        Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show()

        val intent = Intent(this, HomeActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 2000)
    }
}