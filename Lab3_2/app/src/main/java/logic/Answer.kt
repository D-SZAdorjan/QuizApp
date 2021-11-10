data class Answer(var text : String = "", var isCorrect : Boolean = false){
    fun printAnswer(){
        println("$text")
    }
}