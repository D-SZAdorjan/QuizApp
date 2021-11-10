package logic

import Answer
import Question
import QuizController
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var qC : QuizController? = null
    private var questionCounter : Int = 0
    private var pointCounter : Int = 0
    private lateinit var listOfQuestions : MutableList<Question>
    private var numberofQ : Int = 0

    fun getQuizController() : QuizController?{
        return qC
    }

    fun getQuestionList() : MutableList<Question>{
        return listOfQuestions
    }

    fun prepareQuiz(numberQ : Int = 5){
        qC?.randomizeQuestions()
        numberofQ = numberQ
        listOfQuestions = qC?.questions?.subList(0,numberQ)!!
        questionCounter = 0
        pointCounter = 0
    }

    fun setQuizController(qController : QuizController, numberQ : Int = 5){
        qC = qController
        prepareQuiz(numberQ)
    }

    fun getQuestion(nbr : Int) : Question?{
        val q = listOfQuestions.get(nbr)
        q.answers.shuffle()
        return q
    }

    fun loadQuestion() {
        // Do an asynchronous operation to fetch users.
    }

    fun checkAnswerForQuestion(answer : String, qNum : Int){
        val q = listOfQuestions.get(qNum)
        var temp_ans : Answer = Answer()
        when{
            answer == q.answers.get(0).text -> temp_ans = q.answers.get(0)
            answer == q.answers.get(1).text -> temp_ans = q.answers.get(1)
            answer == q.answers.get(2).text -> temp_ans = q.answers.get(2)
            answer == q.answers.get(3).text -> temp_ans = q.answers.get(3)
        }
        if ( temp_ans.isCorrect ){
            pointCounter++
        }
    }

    fun getThePoints() : Int{
        return pointCounter
    }

    fun addPoints(value : Int){
        pointCounter += value
    }

    fun raiseQuestionCounter(){
        questionCounter++
    }

    fun getTheQuestionCounter() : Int{
        return questionCounter
    }

    fun getNumberOfQuestions() : Int{
        return numberofQ
    }
}
