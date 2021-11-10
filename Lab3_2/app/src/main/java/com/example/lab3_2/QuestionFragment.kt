package com.example.lab3_2

import Question
import QuizController
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.lab3_2.databinding.FragmentQuestionBinding
import logic.MyViewModel

class QuestionFragment : Fragment() {

    lateinit var textViewQuestion : TextView
    lateinit var radioGroupAnswers : RadioGroup
    lateinit var radioBtn_temp : RadioButton
    //lateinit var radioBtnAnswer2 : RadioButton
    //lateinit var radioBtnAnswer3 : RadioButton
    //lateinit var radioBtnAnswer4 : RadioButton
    lateinit var btnNext : Button
    //binding
    private var _binding: FragmentQuestionBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val model: MyViewModel by activityViewModels()
    lateinit var question : Question

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        textViewQuestion = binding.questionfragmentQuestion
        radioGroupAnswers = binding.questionfragmentRadiogroup
        //radioBtnAnswer1 = binding.questionfragmentFirstanswer
        //radioBtnAnswer2 = binding.questionfragmentSecondanswer
        //radioBtnAnswer3 = binding.questionfragmentThirdanswer
        //radioBtnAnswer4 = binding.questionfragmentFourthanswer
        btnNext = binding.questionfragmentNextBtn

        question = model.getQuestion(model.getTheQuestionCounter())!!
        textViewQuestion.text = question.text

        for (i in 0..(radioGroupAnswers.childCount-1) ){
            radioBtn_temp = radioGroupAnswers.getChildAt(i) as RadioButton
            radioBtn_temp.text = question.answers[i].text
        }

        if( model.getTheQuestionCounter() == model.getNumberOfQuestions()-1 ){
            btnNext.text = "Submit"
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener{
            var temp_ans = radioGroupAnswers.findViewById(radioGroupAnswers.checkedRadioButtonId) as RadioButton
            model.checkAnswerForQuestion(temp_ans.text.toString(), model.getTheQuestionCounter())
            if( btnNext.text == "Next" ){
                onBtnNextClick(view)
            }
            else{
                onBtnSubmitClick(view)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    // Leave empty do disable back press or
                    // write your code which you want
                    val builder = AlertDialog.Builder(context)
                    builder.setMessage("If you want to exit press the back button again")
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    fun onBtnNextClick(view : View){
        model.raiseQuestionCounter()
        Log.i("btnclick", "Next button clicked!")
        Navigation.findNavController(view).navigate(R.id.navigateQuestionFragmentToItSelf)
    }

    fun onBtnSubmitClick(view : View){
        Log.i("btnclick", "Submit button clicked!")
        Navigation.findNavController(view).navigate(R.id.navigateQuestionFragmentToQuizEndFragment)
    }
}