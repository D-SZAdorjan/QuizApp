package com.example.lab3_2

import Answer
import QuizController
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.lab3_2.databinding.FragmentQuizStartBinding
import com.google.android.material.snackbar.Snackbar

class QuizStartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object{
        const val EXTRA_MESSAGE = "com.example.QuizApp.USERNAME"
    }

    class PickContact : ActivityResultContract<Int, Uri?>() {
        override fun createIntent(context: Context, input: Int?) =
            Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI).also {
                it.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return if (resultCode == AppCompatActivity.RESULT_OK) intent?.data else null
        }
    }

    private val activityRL = registerForActivityResult(PickContact()){
        it?.also { contactUri ->
            val projection = arrayOf(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            )

            this.activity?.contentResolver?.query(contactUri, projection, null, null, null)?.apply{
                moveToFirst()
                etMainEditText.setText(getString(0))
                close()
            }
        }
    }

    private var _binding: FragmentQuizStartBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private lateinit var btnGetStarted : Button
    private lateinit var etMainEditText : EditText
    private lateinit var btnContacts : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.fragment_quiz_start, container, false)
        _binding = FragmentQuizStartBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        btnGetStarted = binding.btnGs
        Log.i("btnGetStarted", btnGetStarted.toString())
        btnContacts = binding.btnContact
        etMainEditText = binding.etMain
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGetStarted.setOnClickListener{
            Log.i("btnGetStarted", "button get started got cllicked!")
            onGSBUttonClick(view)
        }

        btnContacts.setOnClickListener{
            Log.i("btnContacts", "button contacts got cllicked!")
            onContactButtonClick()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onGSBUttonClick(view: View){
        Log.i("GSBTN", "clicked")
        when{
            etMainEditText.text.isBlank() -> Snackbar.make(requireActivity().findViewById(android.R.id.content), "The \"Your name\" field can't be blank", Snackbar.LENGTH_LONG).setAction("Try Again"){}.show()
            etMainEditText.text.all{ !it.isLetter() } -> Snackbar.make(requireActivity().findViewById(android.R.id.content), "The \"Your name\" field should contain only letters", Snackbar.LENGTH_LONG).setAction("Try Again"){ etMainEditText.text.clear() }.show()
            else -> { openQuestionFragment(view) }//openFPActivity() }
        }
    }

    /*fun openFPActivity(){
        val intent = Intent(this, AppFirstPageActivity::class.java).apply{
            putExtra(EXTRA_MESSAGE, etMainEditText.text.toString())
        }
        startActivity(intent)
    }*/

    fun openQuestionFragment(view: View){
        Navigation.findNavController(view).navigate(R.id.navigateQuizStartFragmentToQuestionFragment)
    }

    fun onContactButtonClick(){
        activityRL.launch(0)
    }
}