package com.example.lab3_2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.lab3_2.databinding.FragmentHomeBinding
import com.example.lab3_2.databinding.FragmentQuizStartBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private lateinit var testSkillsBtn: Button
    private lateinit var viewQuestionsBtn: Button
    private lateinit var addQuestionBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        testSkillsBtn = binding.homefragmentTestyourskillsbtn
        viewQuestionsBtn = binding.homefragmentReadquestionsbtn
        addQuestionBtn = binding.homefragmentCreatequestionbtn

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testSkillsBtn.setOnClickListener{
            onTSBtnClick(view)
        }

        viewQuestionsBtn.setOnClickListener {
            onRQBtnClick(view)
        }

        addQuestionBtn.setOnClickListener {
            onCQBtnClick(view)
        }
    }

    private fun onTSBtnClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.navigateHomeFragmentToQuizStartFragment)
    }

    private fun onRQBtnClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.navigateHomeFragmentToQuestionListFragment)
    }

    private fun onCQBtnClick(view: View) {
        Navigation.findNavController(view).navigate(R.id.navigateHomeFragmentToQuestionAddFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}