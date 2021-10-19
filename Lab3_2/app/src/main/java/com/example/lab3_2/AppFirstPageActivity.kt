package com.example.lab3_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class AppFirstPageActivity : AppCompatActivity() {
    private lateinit var user_name : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getSupportActionBar()?.hide()

        Snackbar.make(window.decorView.rootView, "OK", Snackbar.LENGTH_LONG).show()

        setContentView(R.layout.activity_app_first_page)

        user_name = findViewById(R.id.name_of_player)

        val u_name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)

        user_name.setText(u_name)
    }
}