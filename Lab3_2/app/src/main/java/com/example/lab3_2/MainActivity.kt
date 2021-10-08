package com.example.lab3_2

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var btnGetStarted : Button
    private lateinit var etMainEditText : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getSupportActionBar()?.hide()

        super.setContentView(R.layout.activity_main)

        btnGetStarted = findViewById(R.id.btnGs)
        etMainEditText = findViewById(R.id.et_main)

        Log.i("GSBTN", btnGetStarted.toString())

        btnGetStarted.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Log.i("GSBTN", "clicked")
                //Toast.makeText(this@MainActivity, "You clicked the button.", Toast.LENGTH_SHORT).show()
                when{
                    etMainEditText.text.isBlank() -> Snackbar.make(window.decorView.rootView, "The \"Your name\" field can't be blank", Snackbar.LENGTH_LONG).setAction("Try Again"){}.show()
                    etMainEditText.text.all{ !it.isLetter() } -> Snackbar.make(window.decorView.rootView, "The \"Your name\" field should contain only letters", Snackbar.LENGTH_LONG).setAction("Try Again"){ etMainEditText.text.clear() }.show()
                    else -> Snackbar.make(window.decorView.rootView, "OK", Snackbar.LENGTH_LONG).show()
                }
            }

        })
    }
}