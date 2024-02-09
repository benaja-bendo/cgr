package com.example.cgr

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val helloTextView = findViewById<TextView>(R.id.text_view_id)
        val userGreeting = getString(R.string.user_greeting)
        helloTextView.setText(R.string.user_greeting)
    }
}