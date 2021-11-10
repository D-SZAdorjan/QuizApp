data class Question(var text: String = "", var answers: MutableList<Answer> = mutableListOf()){
    fun getCorrectAnswer(): Answer?{
        return answers.find { it.isCorrect.equals(true) }
    }
    fun printQuestion(){
        println("$text")
        answers.forEach{ it.printAnswer() }
    }
}