package com.example.cgr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class AddFilmToPanierActivity : BaseActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film_to_panier)

        val intent = intent

        val titleFilm = intent.getStringExtra("titleFilm")
        setHeaderTitle(titleFilm)

        val graphicUrl = intent.getStringExtra("graphicUrl")
        val imageViewGraphicUrl = findViewById<ImageView>(R.id.imageViewGraphicUrl)
        Glide.with(this).load(graphicUrl).into(imageViewGraphicUrl);

        val description = intent.getStringExtra("description")
        val textViewDescription = findViewById<TextView>(R.id.textViewDescription)
        textViewDescription.text = description

        val runTime = intent.getStringExtra("runTime")
        val textViewRunTime = findViewById<TextView>(R.id.textViewRunTime)
        textViewRunTime.text = runTime
    }
}