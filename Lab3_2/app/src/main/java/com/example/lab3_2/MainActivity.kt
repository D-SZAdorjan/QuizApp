package com.example.lab3_2

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnGetStarted : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getSupportActionBar()?.hide()

        super.setContentView(R.layout.activity_main)

        btnGetStarted = findViewById(R.id.btnGs)

        Log.i("GSBTN", btnGetStarted.toString())

        btnGetStarted.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                Log.i("GSBTN", "clicked")
                Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            }

        })
        setContentView(R.layout.activity_main)
    }
}