package com.angie.quetantomeconoces

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var questionText: TextView? = null
    private var resultText: TextView? = null
    private var option1: Button? = null
    private var option2: Button? = null
    private var option3: Button? = null
    private var option4: Button? = null
    private val questions = arrayOf(
        arrayOf("¿Qué prefiere Cami hacer los fines de semana?", "Dormir", "Salir con sus amigos", "Hacer la tarea", "Ordenar la habitación", "Salir con sus amigos"),
        arrayOf("¿Cuál es la materia favorita de Cami?", "Inglés", "Ciudadanía y participación", "Educación física", "Química", "Inglés"),
        arrayOf("¿Quién es la mejor amiga de Cami?", "Jaz", "Hada", "Faty", "Luz", "Faty"),
        arrayOf("¿Qué hace Cami cuando tiene frío?", "Abrigarse", "Leer", "Acostarse en la cama", "Preparar comida caliente", "Acostarse en la cama"),
        arrayOf("¿Dónde vive Cami?", "Córdoba Capital", "La Plata", "Capilla del Monte", "City Bell", "Capilla del Monte"),
        arrayOf("¿Qué le gusta hacer a Cami cuando se junta con sus amigas?", "Grabar Tik Toks", "Dormir la siesta", "Estudiar", "Contar chismes", "Contar chismes"),
        arrayOf("¿A quién prefiere Cami?", "A su mamá", "A su papá", "A Ceci", "A sus hermanas", "A su mamá"),
        arrayOf("¿Cómo se pone Cami cuando hace calor?", "De mal humor", "Feliz", "Triste", "Estresada", "De mal humor"),
        arrayOf("¿Cuál es la orientación sexual de Cami?", "Lesbiana", "Bisexual", "Heterosexual", "Ninguna de las anteriores", "Bisexual")
    )
    private var currentQuestion = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        questionText = findViewById(R.id.questionText)
        resultText = findViewById(R.id.resultText)
        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)

        // Cargar la primera pregunta
        loadQuestion()

        // Listener para las opciones de respuesta
        val answerListener = View.OnClickListener { view ->
            val selectedOption = view as Button
            checkAnswer(selectedOption)
        }

        // Asignar el listener a los botones de las opciones
        option1!!.setOnClickListener(answerListener)
        option2!!.setOnClickListener(answerListener)
        option3!!.setOnClickListener(answerListener)
        option4!!.setOnClickListener(answerListener)
    }

    private fun loadQuestion() {
        if (currentQuestion < questions.size) {
            // Mostrar pregunta y opciones
            questionText!!.text = questions[currentQuestion][0]
            option1!!.text = questions[currentQuestion][1]
            option2!!.text = questions[currentQuestion][2]
            option3!!.text = questions[currentQuestion][3]
            option4!!.text = questions[currentQuestion][4]

            // Asegurarse de que los botones sean visibles y estén habilitados
            option1!!.visibility = View.VISIBLE
            option2!!.visibility = View.VISIBLE
            option3!!.visibility = View.VISIBLE
            option4!!.visibility = View.VISIBLE
            option1!!.isEnabled = true
            option2!!.isEnabled = true
            option3!!.isEnabled = true
            option4!!.isEnabled = true

            // Restablecer los colores de los botones a blanco antes de cargar una nueva pregunta
            resetButtonColors()

            // Limpiar el mensaje de resultado
            resultText!!.text = ""
        } else {
            // Fin del cuestionario, mostrar puntaje
            questionText!!.text = "¡Cuestionario finalizado!"
            resultText!!.text = "Puntaje: $score/${questions.size}"

            // Mostrar mensaje final según puntaje
            resultText!!.append("\n" + getResultMessage())

            // Ocultar las opciones al finalizar el cuestionario
            option1!!.visibility = View.GONE
            option2!!.visibility = View.GONE
            option3!!.visibility = View.GONE
            option4!!.visibility = View.GONE
        }
    }

    private fun checkAnswer(selectedOption: Button) {
        val correctAnswer = questions[currentQuestion][5]

        if (selectedOption.text.toString() == correctAnswer) {
            resultText!!.text = "¡Correcto!"
            selectedOption.setBackgroundColor(Color.GREEN) // Botón verde si la respuesta es correcta
            score++ // Aumentar puntaje si la respuesta es correcta
        } else {
            resultText!!.text = "Incorrecto. La respuesta correcta es: $correctAnswer"
            selectedOption.setBackgroundColor(Color.RED) // Botón rojo si la respuesta es incorrecta
        }

        // Deshabilitar los botones después de responder
        option1!!.isEnabled = false
        option2!!.isEnabled = false
        option3!!.isEnabled = false
        option4!!.isEnabled = false

        // Avanzar a la siguiente pregunta después de un breve retraso
        currentQuestion++
        if (currentQuestion < questions.size) {
            // Esperar un breve momento antes de cargar la siguiente pregunta
            resultText!!.postDelayed({
                loadQuestion()
            }, 1500) // Un segundo de retraso
        } else {
            // Fin del cuestionario si ya no hay más preguntas
            loadQuestion()
        }
    }

    // Método para restablecer los colores de los botones a blanco
    private fun resetButtonColors() {
        // Establecer el color de fondo de los botones a blanco
        option1!!.setBackgroundColor(Color.WHITE)
        option2!!.setBackgroundColor(Color.WHITE)
        option3!!.setBackgroundColor(Color.WHITE)
        option4!!.setBackgroundColor(Color.WHITE)
    }

    // Método para obtener el mensaje basado en el puntaje
    private fun getResultMessage(): String {
        return when {
            score == questions.size -> "¡Conoces mucho a la persona!"
            score >= questions.size / 2 -> "¡No está mal, pero puedes mejorar!"
            else -> "¡Parece que no conoces mucho a la persona!"
        }
    }
}
