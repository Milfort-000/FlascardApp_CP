package com.example.flashcarrapp_cp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY






class MainActivity : AppCompatActivity() {
    private var isShowingAnswer = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //val flashcardQuestion = findViewById<TextView>(R.id. flashcard_question)
        //val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
       // flashcardQuestion.setOnClickListener {
            //flashcardQuestion.visibility = View.INVISIBLE
            //flashcardAnswer.visibility = View.VISIBLE


        /*flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = View.INVISIBLE
        }*/

          // New logic

          val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
          val flashcardAnswer1 = findViewById<TextView>(R.id.flashcard_answer1)
          val flashcardAnswer2 = findViewById<TextView>(R.id.flashcard_answer2)
          val flashcardAnswer3 = findViewById<TextView>(R.id.flashcard_answer3)

        // Bonne réponse Barack Obama

        val correctAnswer = flashcardAnswer3

        val options = listOf<TextView>(flashcardAnswer1, flashcardAnswer2, flashcardAnswer3)

        // Gérer les clics sur chaque réponse

        for (option in options) { option.setOnClickListener {
            // Rénitialiser les couleurs

            for (opt in options) { opt.setBackgroundColor(Color.parseColor("#FFCC80"))}

            // Vérifie si la réponse est correcte

            if (option == correctAnswer) {
                option.setBackgroundColor(Color.parseColor("#A5D6A7")) //} //VERT

               Toast.makeText(this,"Correct", Toast.LENGTH_SHORT).show()
            } else {
                option.setBackgroundColor(Color.parseColor("#EF9A9A")) // ROUGE

                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show()

            }
        }


        var toggleIcon = findViewById<ImageView>(R.id.yeuxouvert)
        var choicesLayout = findViewById<LinearLayout>(R.id.choicesLayout)


            // Clic sur l'icone oeil
        toggleIcon.setOnClickListener {
            isShowingAnswer = ! isShowingAnswer // on inverse l'état

            if (isShowingAnswer) {
                // Afficher les réponses
                choicesLayout.visibility = TextView.VISIBLE
                toggleIcon.setImageResource(R.drawable.show_icon) // yeux ouvert

            } else { // Cacher les réponses
                choicesLayout.visibility = TextView.GONE

                toggleIcon.setImageResource(R.drawable.hide_icon) // yeux ferme
            }

        }
    }
}
}