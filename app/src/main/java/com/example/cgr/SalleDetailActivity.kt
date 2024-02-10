package com.example.cgr

import android.os.Bundle
import android.widget.TextView

class SalleDetailActivity : BaseActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salle_detail)

        val intent = intent

        val name = intent.getStringExtra("nameSalle")
        setHeaderTitle(name)

        val address1 = intent.getStringExtra("address1")
        val textViewAddress1 = findViewById<TextView>(R.id.textViewAddress)
        textViewAddress1.text = address1

        val address2 = intent.getStringExtra("address2")
        val textViewAddress2 = findViewById<TextView>(R.id.textViewAddress2)
        textViewAddress2.text = address2

        val description = intent.getStringExtra("descriptionSalle")
        val textViewDescription = findViewById<TextView>(R.id.textViewDescriptionSalle)
        textViewDescription.text = description

        val parkingInfo = intent.getStringExtra("parkingInfo")
        val textViewParkingInfo = findViewById<TextView>(R.id.textViewParkingInfo)
        textViewParkingInfo.text = parkingInfo

        val transport = intent.getStringExtra("publicTransport")
        val textViewTransport = findViewById<TextView>(R.id.textViewTransport)
        textViewTransport.text = transport
    }
}