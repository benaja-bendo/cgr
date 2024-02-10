package com.example.cgr

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val resultLancher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val qrCode = data?.getStringExtra(ScanQRActivity.QR_CODE_KEY)
                val intent = Intent(this, FormManuelActivity::class.java)
                intent.putExtra("QRCode", qrCode)
                startActivity(intent)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()

        val scanQRButton = findViewById<Button>(R.id.scanQRButton)
        val formManuelButton = findViewById<Button>(R.id.fillFormButton)

        scanQRButton.setOnClickListener {
//            val intent = Intent(this, ScanQRActivity::class.java)
//            startActivity(intent)
            startScanQRActivity()
        }
        formManuelButton.setOnClickListener {
            val intent = Intent(this, FormManuelActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loadData() {
        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        println("sharedPreferences.contains(\"name\")")
        println(sharedPreferences.contains("name"))
        if (name != "") {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun startScanQRActivity() {
        val intent = Intent(this, ScanQRActivity::class.java)
        resultLancher.launch(intent)
    }
}