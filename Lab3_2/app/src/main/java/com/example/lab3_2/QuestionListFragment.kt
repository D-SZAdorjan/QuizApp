package com.example.lab3_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3_2.databinding.FragmentHomeBinding
import com.example.lab3_2.databinding.FragmentQuestionListBinding
import logic.MyRViewAdapter
import logic.MyViewModel

class QuestionListFragment : Fragment() {

    private var _binding: FragmentQuestionListBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MyRViewAdapter.ViewHolder>? = null

    private val model: MyViewModel by activityViewModels()

    private lateinit var recView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuestionListBinding.inflate(inflater, container, false)
        val view = binding.root
        recView = binding.fragmentQLRecyclerView
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(context)
        recView.layoutManager = layoutManager

        adapter = MyRViewAdapter(context,model.getQuizController()!!.questions)
        recView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}