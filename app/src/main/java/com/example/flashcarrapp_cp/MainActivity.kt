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

import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.View.INVISIBLE
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar




class MainActivity : AppCompatActivity() {
   /* private var isShowingAnswer = false */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)



        val flashcardQuestion = findViewById<TextView>(R.id. flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer3)
       flashcardQuestion.setOnClickListener {
           flashcardQuestion.visibility = INVISIBLE
           flashcardAnswer.visibility = View.VISIBLE
       }


        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = INVISIBLE
        }

          // New logic

        /*  val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
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
    } */
          /* var cercle_plus_icon = findViewById<TextView>(R.id.cercle_icon_plus)
               cercle_plus_icon.setOnClickListener {
                   val intent = intent(this, AjouterActiviteCarte::class.java)
                   startActivity(intent)
               }
           */



        var cercle_plus_icon = findViewById<ImageView>(R.id.cercle_plus_icon)
       /* var cercle_annuler_icon = findViewById<ImageView>(R.id.cercle_annuler_icon) */
        /*var save_icon = findViewById<ImageView>(R.id.save_icon)*/


        // Préparation du resultLauncher pour recevoir le résultat
        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data: Intent? = result.data
            if (data != null) {
                val string1 = data.getStringExtra("string1")
                val string2 = data.getStringExtra("string2")

                Log.i("MainActivity", "string1: $string1")
                Log.i("MainActivity", "string2: $string2")

                // Remplacer la carte par defaut par la nouvelle carte

                flashcardQuestion.text = "Q:$string1/n(Click to reaveal answer)"
                flashcardAnswer.text = "R:$string2"
                flashcardAnswer.visibility = View.INVISIBLE

                Snackbar.make(
                    findViewById(R.id.flashcard_question),
                    "Carte enregistrée : $string1 - $string2",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Log.i("MainActivity", "Returned null data from AddCardActivity")
            }

          /*  // Après retour, on remet les boutons dans leur état initial
            cercle_annuler_icon.visibility = View.GONE
            cercle_plus_icon.visibility = View.VISIBLE

           */
        }

           cercle_plus_icon = findViewById<ImageView>(R.id.cercle_plus_icon)
            /*save_icon = findViewById(R.id.save_icon)*/

            // Clique sur "+"
            cercle_plus_icon.setOnClickListener {
                // Changer la visibilité des boutons
               /* cercle_plus_icon.visibility = View.INVISIBLE
                cercle_annuler_icon.visibility = View.VISIBLE */

                // Ouvrir AddCardActivity
                val intent = Intent(this, AjouterActiviteCarte::class.java)
                resultLauncher.launch(intent)
            }



           /* // Clique sur "Enregistrer" (ici tu peux ajouter ta logique locale)
            save_icon.setOnClickListener {
                Snackbar.make(
                    findViewById(R.id.flashcard_question),
                    "Enregistrement local effectué",
                    Snackbar.LENGTH_SHORT
                ).show()
            } */







        }
}