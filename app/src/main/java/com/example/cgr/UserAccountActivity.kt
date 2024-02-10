package com.example.cgr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import androidx.appcompat.widget.Toolbar

//import kotlinx.android.synthetic.main.activity_user_account.*

class UserAccountActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_account)

        showBack()
        setHeaderTitle(getString(R.string.txtUserAccount))


        val editTextTextLastName=findViewById<EditText>(R.id.editTextTextLastName)
        val editTextTextName=findViewById<EditText>(R.id.editTextTextName)
        val buttonUpdate=findViewById<Button>(R.id.buttonUpdate)
        val editTextTextEmail=findViewById<EditText>(R.id.editTextTextEmail)
        val editTextTextAddress=findViewById<EditText>(R.id.editTextTextAddress)
        val editTextTextZipCode=findViewById<EditText>(R.id.editTextTextZipCode)
        val editTextTextCity=findViewById<EditText>(R.id.editTextTextCity)
        buttonUpdate.setOnClickListener(View.OnClickListener {
            Toast.makeText(application,editTextTextAddress.text.toString()+"/"
                    +editTextTextEmail.text.toString()+"/"
                    +editTextTextName.text.toString()+"/"
                    +editTextTextLastName.text.toString()+"/"
                    +editTextTextZipCode.text.toString()+"/"
                    +editTextTextCity.text.toString()+"/",
                Toast.LENGTH_LONG).show()
        })

    }
}