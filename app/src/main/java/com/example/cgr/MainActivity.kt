package com.example.cgr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanQRButton = findViewById<Button>(R.id.scanQRButton)
        val formManuelButton = findViewById<Button>(R.id.fillFormButton)

        scanQRButton.setOnClickListener {
            val intent = Intent(this, ScanQRActivity::class.java)
            startActivity(intent)
        }
        formManuelButton.setOnClickListener {
            val intent = Intent(this, FormManuelActivity::class.java)
            startActivity(intent)
        }

    }
}