package com.example.lab3_2

import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MESSAGE = "com.example.QuizApp.USERNAME"
    }

    class PickContact : ActivityResultContract<Int, Uri?>() {
        override fun createIntent(context: Context, input: Int?) =
            Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI).also {
                it.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return if (resultCode == RESULT_OK) intent?.data else null
        }
    }

    private lateinit var btnGetStarted : Button
    private lateinit var etMainEditText : EditText
    private lateinit var btnContacts : Button

    private val activityRL = registerForActivityResult(PickContact()){
        it?.also { contactUri ->
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )

            contentResolver.query(contactUri, projection, null, null, null)?.apply{
                moveToFirst()
                etMainEditText.setText(getString(0))
                close()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.getSupportActionBar()?.hide()

        super.setContentView(R.layout.activity_main)

        btnGetStarted = findViewById(R.id.btnGs)
        etMainEditText = findViewById(R.id.et_main)
        btnContacts = findViewById(R.id.btnContact)

        Log.i("GSBTN", "The app got created!")

        btnGetStarted.setOnClickListener{
            onGSBUttonClick()
        }

        btnContacts.setOnClickListener{
            onContactButtonClick()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("GSBTN", "The app got Started!")
    }

    override fun onResume() {
        super.onResume()
        Log.i("GSBTN", "The app got Resumed!")
    }

    override fun onStop() {
        super.onStop()
        Log.i("GSBTN", "The app got Stopped!")
    }

    override fun onPause() {
        super.onPause()
        Log.i("GSBTN", "The app got Paused!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("GSBTN", "The app got Destroyed!")
    }

    fun onGSBUttonClick(){
        Log.i("GSBTN", "clicked")
        when{
            etMainEditText.text.isBlank() -> Snackbar.make(window.decorView.rootView, "The \"Your name\" field can't be blank", Snackbar.LENGTH_LONG).setAction("Try Again"){}.show()
            etMainEditText.text.all{ !it.isLetter() } -> Snackbar.make(window.decorView.rootView, "The \"Your name\" field should contain only letters", Snackbar.LENGTH_LONG).setAction("Try Again"){ etMainEditText.text.clear() }.show()
            else -> { openFPActivity() }
        }
    }

    fun openFPActivity(){
        val intent = Intent(this, AppFirstPageActivity::class.java).apply{
            putExtra(EXTRA_MESSAGE, etMainEditText.text.toString())
        }
        startActivity(intent)
    }

    fun onContactButtonClick(){
        activityRL.launch(0)
    }
}