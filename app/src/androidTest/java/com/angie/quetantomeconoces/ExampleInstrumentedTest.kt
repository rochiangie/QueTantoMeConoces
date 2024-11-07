package com.angie.quetantomeconoces

class Questionnaire {
    var totalQuestions: Int = 0
    var correctAnswers: Int = 0

    // Métodos para manejar las respuestas y el puntaje
    fun addAnswer(isCorrect: Boolean) {
        if (isCorrect) {
            correctAnswers++
        }
        totalQuestions++
    }

    fun getScore(): String {
        return if (totalQuestions > 0) {
            val scorePercentage = (correctAnswers.toDouble() / totalQuestions.toDouble()) * 100
            "Tu puntaje es: $scorePercentage%"
        } else {
            "No hay respuestas registradas"
        }
    }

    fun getResultMessage(): String {
        val scorePercentage = (correctAnswers.toDouble() / totalQuestions.toDouble()) * 100
        return if (scorePercentage >= 70) {
            "¡Conoces mucho a la persona!"
        } else {
            "Parece que no la conoces tan bien."
        }
    }
}
