package com.angie.quetantomeconoces

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QuestionnaireTest {

    private lateinit var questionnaire: Questionnaire

    @Before
    fun setUp() {
        // Inicializamos la clase Questionnaire antes de cada prueba
        questionnaire = Questionnaire()
    }

    @Test
    fun testAddAnswerCorrect() {
        // Simula que se da una respuesta correcta
        questionnaire.addAnswer(true)

        // Verifica que el contador de respuestas correctas sea 1
        assertEquals(1, questionnaire.correctAnswers)
        assertEquals(1, questionnaire.totalQuestions)
    }

    @Test
    fun testAddAnswerIncorrect() {
        // Simula que se da una respuesta incorrecta
        questionnaire.addAnswer(false)

        // Verifica que el contador de respuestas correctas no cambie
        assertEquals(0, questionnaire.correctAnswers)
        assertEquals(1, questionnaire.totalQuestions)
    }

    @Test
    fun testGetScore() {
        // Simula respuestas correctas e incorrectas
        questionnaire.addAnswer(true)  // Respuesta correcta
        questionnaire.addAnswer(false) // Respuesta incorrecta
        questionnaire.addAnswer(true)  // Respuesta correcta

        // Verifica el cálculo del puntaje
        val score = questionnaire.getScore()
        assertTrue(score.contains("Tu puntaje es: 66.67"))  // Asegúrate de que el formato coincida
    }

    @Test
    fun testGetResultMessageHighScore() {
        // Simula respuestas correctas con un puntaje alto
        questionnaire.addAnswer(true)
        questionnaire.addAnswer(true)
        questionnaire.addAnswer(true)

        val result = questionnaire.getResultMessage()
        assertEquals("¡Conoces mucho a la persona!", result)
    }

    @Test
    fun testGetResultMessageLowScore() {
        // Simula respuestas incorrectas con un puntaje bajo (menos del 50%)
        questionnaire.addAnswer(false)  // Incorrecta
        questionnaire.addAnswer(false)  // Incorrecta

        val result = questionnaire.getResultMessage()
        assertEquals("Parece que no la conoces tan bien.", result)
    }

}
