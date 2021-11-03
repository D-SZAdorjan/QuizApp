package com.example.lab3_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.lab3_2.databinding.FragmentQuizEndBinding
import com.example.lab3_2.databinding.FragmentQuizStartBinding
import logic.MyViewModel

class QuizEndFragment : Fragment() {
    private var _binding: FragmentQuizEndBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model: MyViewModel by activityViewModels()

    lateinit var resultQuizEnd : TextView
    lateinit var btnQuizEnd : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentQuizEndBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        resultQuizEnd = binding.quizendfragmentResult
        btnQuizEnd = binding.quizendfragmentTAbtn

        resultQuizEnd.text = model.getThePoints().toString() + "/" + model.getNumberOfQuestions().toString()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnQuizEnd.setOnClickListener{
            onTryAgainBtnClicked(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onTryAgainBtnClicked(view : View){
        model.prepareQuiz()
        Navigation.findNavController(view).navigate(R.id.navigateQuizEndFragmentToQuizStartFragment)
    }
}