package com.angie.quetantomeconoces

class Questionnaire {
    var correctAnswers = 0
    var totalQuestions = 0

    fun addAnswer(isCorrect: Boolean) {
        totalQuestions++
        if (isCorrect) {
            correctAnswers++
        }
    }

    fun getScore(): String {
        val scorePercentage = (correctAnswers.toDouble() / totalQuestions) * 100
        return "Tu puntaje es: %.2f".format(scorePercentage)
    }

    fun getResultMessage(): String {
        val scorePercentage = (correctAnswers.toDouble() / totalQuestions) * 100
        return when {
            scorePercentage >= 80 -> "¡Conoces mucho a la persona!"
            scorePercentage >= 50 -> "Parece que la conoces bien."
            else -> "Parece que no la conoces tan bien."
        }
    }
}