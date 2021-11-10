import android.content.Context
import com.example.lab3_2.R

class QuizController(context : Context){
    var questions : MutableList<Question> = mutableListOf()

    init{
        var temp_Q : Question = Question()
        var i = 0
        //val path = "/home/demen/SzamtechI_IV/QuizApp/Lab3_2/app/src/main/java/data/quiz.txt.txt"
        //val path = "D:\\Szamtech_I-IV\\Android\\Android_2021\\Lab3\\src\\main\\kotlin\\quiz.txt.txt"
        val lineList = context.resources.openRawResource(R.raw.quiz).bufferedReader().useLines { it.toList() }
        /*File(path).forEachLine{*/
        lineList.forEach{
            if( i % 5 == 0){
                if( i != 0){
                    //temp_Q.printQuestion()
                    questions.add(Question(temp_Q.text, temp_Q.answers))
                    temp_Q.text = ""
                    temp_Q.answers = mutableListOf()
                }
                temp_Q.text = it
            }
            else{
                var temp_list = it.split("//")
                var temp_Ans = Answer(temp_list[0],temp_list[1].toBoolean())
                temp_Q.answers.add(temp_Ans)
            }
            //println(it)
            i += 1
        }
        //temp_Q.printQuestion()
        questions.add(Question(temp_Q.text, temp_Q.answers))
    }

    fun printQuiz(){
        questions.forEach{ it.printQuestion() }
    }

    fun randomizeQuestions(){
        questions.shuffle()
    }

    fun doQuiz(numberQ : Int){
        this.randomizeQuestions()
        val listOfQuestions = this.questions.subList(0,numberQ)
        var answer : String?
        var correctAnswers : Int = 0
        listOfQuestions.forEach{
            it.answers.shuffle()
            it.printQuestion()
            println()
            println("Write your answer (1,2,3,4): ")
            var temp_ans : Answer = Answer()
            answer = readLine()
            when{
                answer == "1" -> temp_ans = it.answers[0]
                answer == "2" -> temp_ans = it.answers[1]
                answer == "3" -> temp_ans = it.answers[2]
                answer == "4" -> temp_ans = it.answers[3]
            }
            if ( temp_ans.isCorrect ){
                correctAnswers++
            }
        }
        println()
        println("Your correct answers: $correctAnswers/$numberQ")
    }
}