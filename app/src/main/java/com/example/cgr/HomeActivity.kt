package com.example.cgr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    val filmsFragment= FilmsFragment.newInstance("","")
    val sallesFragment= SallesFragment.newInstance("","")
    val carteFragment= CarteFragment.newInstance("","")
    val panierFragment= CarteFragment.newInstance("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val buttonProfile = findViewById<ImageButton>(R.id.imageButtonProfile)
        buttonProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        val films = findViewById<TextView>(R.id.textViewFilms)
        val salles = findViewById<TextView>(R.id.textViewSalles)
        val carte = findViewById<TextView>(R.id.textViewCarte)
        val panier = findViewById<TextView>(R.id.textViewPanier)

        showFilms()

        films.setOnClickListener {
            showFilms()
        }

        salles.setOnClickListener {
            showSalles()
        }

        carte.setOnClickListener {
            showCarte()
        }

        panier.setOnClickListener {
            showPanier()
        }
    }
        fun showFilms(){
            val frManager=supportFragmentManager
            val fragmentTra= frManager.beginTransaction()
            fragmentTra.addToBackStack("Films")
            fragmentTra.replace(R.id.layoutContent,filmsFragment)
            fragmentTra.commit()
        }

        fun showSalles(){
            val frManager=supportFragmentManager
            val fragmentTra= frManager.beginTransaction()
            fragmentTra.addToBackStack("Salles")
            fragmentTra.replace(R.id.layoutContent,sallesFragment)
            fragmentTra.commit()
        }

        fun showCarte(){
            val frManager=supportFragmentManager
            val fragmentTra= frManager.beginTransaction()
            fragmentTra.addToBackStack("Carte")
            fragmentTra.replace(R.id.layoutContent,carteFragment)
            fragmentTra.commit()
        }

        fun showPanier(){
            val frManager=supportFragmentManager
            val fragmentTra= frManager.beginTransaction()
            fragmentTra.addToBackStack("Panier")
            fragmentTra.replace(R.id.layoutContent,panierFragment)
            fragmentTra.commit()
        }
}