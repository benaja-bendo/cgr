package com.example.cgr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView


open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.d("CGR","############### onCreate ###############"+javaClass.simpleName)
        }

    override fun onStart() {
            super.onStart()
            Log.d("CGR","############### onStart ###############"+javaClass.simpleName)
        }

        override fun onStop() {
            super.onStop()
            Log.d("CGR","############### onStop ###############"+javaClass.simpleName)
        }

        override fun onResume() {
            super.onResume()
            Log.d("CGR","############### onResume ###############"+javaClass.simpleName)
        }

        override fun onRestart() {
            super.onRestart()
            Log.d("CGR","############### onRestart ###############"+javaClass.simpleName)
        }

        override fun onPause() {
            super.onPause()
            Log.d("CGR","############### onPause ###############"+javaClass.simpleName)
        }
        override fun onDestroy() {
            super.onDestroy()
            Log.d("CGR","############### onDestroy ###############"+javaClass.simpleName)
        }

    fun showBack(){
        val imageViewBack = findViewById<ImageView>(R.id.imageViewBack)
        imageViewBack.visibility=View.VISIBLE
        imageViewBack.setOnClickListener {
            this.finish()
        }
    }

    fun setHeaderTitle(title:String?){
        if(title!=null) {
            val textViewNature = findViewById<TextView>(R.id.textViewTitle)
            textViewNature.text = title
        }
    }

}