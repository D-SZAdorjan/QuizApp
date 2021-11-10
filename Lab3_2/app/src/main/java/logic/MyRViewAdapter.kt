package logic

import Question
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.lab3_2.R

class MyRViewAdapter(var context: Context?, private val dataSet: MutableList<Question>) :
    RecyclerView.Adapter<MyRViewAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var question: TextView
        var answer: TextView
        var detailBtn: Button
        var deleteBtn: Button

        init {
            // Define click listener for the ViewHolder's View.
            question = view.findViewById(R.id.questionItem_question)
            answer = view.findViewById(R.id.questionItem_answer)
            detailBtn = view.findViewById(R.id.questionItem_detailsBtn)
            deleteBtn = view.findViewById(R.id.questionItem_deleteBtn)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.question_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.question.text = dataSet[position].text
        viewHolder.answer.text = dataSet[position].getCorrectAnswer()?.text
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
