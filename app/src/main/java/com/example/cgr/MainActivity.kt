package com.example.cgr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText

class MainActivity : AppCompatActivity() {

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

    private fun startScanQRActivity() {
        val intent = Intent(this, ScanQRActivity::class.java)
        resultLancher.launch(intent)
    }
}