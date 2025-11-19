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
import android.view.ViewAnimationUtils
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar


lateinit var flashcardDatabase: FlashcardDatabase
var allFlashcard = mutableListOf<Flashcard>()

class MainActivity : AppCompatActivity() {
   /* private var isShowingAnswer = false */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

       //allFlashcard = flashcardDatabase.getAllCards().toMutableList()

/* val flashcardDb = FlashcardDatabase(this)
       flashcardDb.initFirstCard()
       Log.d("DB_TEST", flashcardDb.getAllCards().toString()) */

        val flashcardQuestion = findViewById<TextView>(R.id. flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer3)
       flashcardQuestion.setOnClickListener {
           val answerSideView = flashcardAnswer
           val questionSideView = flashcardQuestion
           // centre du cercle
           val cx = answerSideView.width / 2
           val cy = answerSideView.height / 2
           // Rayon final
           val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
           val anim = ViewAnimationUtils.createCircularReveal(answerSideView,
               cx,
               cy,
               0f,
               finalRadius)

           questionSideView.visibility = View.INVISIBLE
           answerSideView.visibility = View.VISIBLE

           anim.duration = 500
           anim.start()

           //flashcardQuestion.visibility = INVISIBLE
          // flashcardAnswer.visibility = View.VISIBLE
       }


        flashcardAnswer.setOnClickListener {
            flashcardQuestion.visibility = View.VISIBLE
            flashcardAnswer.visibility = INVISIBLE
        }

       flashcardDatabase = FlashcardDatabase(this)
       // Create default card only the FIRST TIME (si DB vide)
       if (flashcardDatabase.getAllCards().isEmpty()) {
           flashcardDatabase.insertCard(
               Flashcard(
                   "Who is the 44th president of the United States?",
                   "Barack Obama"
               )
           )
       }

       // Charger toutes les cartes
      // allFlashcard = flashcardDatabase.getAllCards().toMutableList()
       //flashcardDatabase.initFirstCard()
       allFlashcard = flashcardDatabase.getAllCards().toMutableList()

       if (allFlashcard.isNotEmpty()) {
           flashcardQuestion.text = allFlashcard[0].question
           flashcardAnswer.text = allFlashcard[0].answer
       }

       /*if (allFlashcard.size > 0) {
           findViewById<TextView>(R.id.flashcard_question).text = allFlashcard[0].question
           findViewById<TextView>(R.id.flashcard_answer3).text = allFlashcard[0].answer
       }*/

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
       /* val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data: Intent? = result.data
            val extras = data?.extras
            if (extras != null) { // Check that we have data returned
                val string1 = extras.getString("string1") ?: ""
                val string2 = extras.getString("string2") ?: ""

                Log.i("MainActivity", "string1: $string1")
                Log.i("MainActivity", "string2: $string2")

                // Save newly created flashcard database
                if (string1 != null && string2 != null) {
                    flashcardDatabase.insertCard(Flashcard(string1, string2))
                    // Update set of flascards to include new card
                    allFlashcard = flashcardDatabase.getAllCards().toMutableList()
                } else {
                    Log.e("TAG", "Missing question or answer to input into database. Question is $string1 and answer is $string2")
                }

                // Remplacer la carte par defaut par la nouvelle carte

                flashcardQuestion.text = "Q:$string1\n(Click to reaveal answer)"
                flashcardAnswer.text = "R:$string2"
                flashcardAnswer.visibility = View.INVISIBLE

                flashcardDb.insertCard(Flashcard(string1, string2))
                //val allCards = flashcardDb.getAllCards()
                if (allFlashcard.isNotEmpty()) {
                    val lastCard = allFlashcard.last()
                    flashcardQuestion.text = "Q: ${lastCard.question}\n(Click to reveal answer)"
                    flashcardAnswer.text = "R: ${lastCard.answer}"
                    flashcardAnswer.visibility = View.INVISIBLE
                }

                Snackbar.make(
                    findViewById(R.id.flashcard_question),
                    "Carte enregistrée : $string1 - $string2",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Log.i("MainActivity", "Returned null data from AddCardActivity")
            } */

       val resultLauncher = registerForActivityResult(
           ActivityResultContracts.StartActivityForResult()
       ) { result ->
           val data = result.data

           if (data != null && result.resultCode == RESULT_OK) {
               val question = data.getStringExtra("string1") ?: ""
               val answer = data.getStringExtra("string2") ?: ""

               if (question.isNotEmpty() && answer.isNotEmpty()) {

                   // Sauvegarde en base
                   flashcardDatabase.insertCard(Flashcard(question, answer))

                   // Recharge la liste
                   allFlashcard = flashcardDatabase.getAllCards().toMutableList()

                   // Affiche la dernière carte
                   val last = allFlashcard.last()
                   flashcardQuestion.text = last.question
                   flashcardAnswer.text = last.answer
                   flashcardAnswer.visibility = View.INVISIBLE

                   Snackbar.make(
                       findViewById(R.id.flashcard_question),
                       "Carte enregistrée",
                       Snackbar.LENGTH_SHORT
                   ).show()
               }
           }





          /*  // Après retour, on remet les boutons dans leur état initial
            cercle_annuler_icon.visibility = View.GONE
            cercle_plus_icon.visibility = View.VISIBLE

           */
        }

       val edit_icon = findViewById<ImageView>(R.id.edit_icon)
       edit_icon.setOnClickListener {
           val intent = Intent(this, AjouterActiviteCarte::class.java)

           // Passer la question et reponse actuelles a AjouterActiviteCarte pour les editer
           intent.putExtra(
               "string1",
               flashcardQuestion.text.toString().replace("(Click to reveal answer)", "").trim()
           )
           intent.putExtra(
               "string2",
               flashcardAnswer.text.toString().replace("(R:)", "").trim()
           )
           resultLauncher.launch(intent)
           overridePendingTransition(R.anim.right_in, R.anim.left_out)
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
                overridePendingTransition(R.anim.right_in, R.anim.left_out)
            }
       var currentCardDisplayIndex = 0
       val next_icon = findViewById<ImageView>(R.id.next_icon)
       next_icon.setOnClickListener { v ->
           val leftOut = AnimationUtils.loadAnimation(v.context, R.anim.left_out)
           val rightIn = AnimationUtils.loadAnimation(v.context, R.anim.right_in)

           leftOut.setAnimationListener(object : Animation.AnimationListener {
               override fun onAnimationStart(animation: Animation?){}
               override fun onAnimationEnd(animation: Animation?){
                   //Charger la prochaine carte
                   currentCardDisplayIndex++
                   if (currentCardDisplayIndex >= allFlashcard.size) {
                       currentCardDisplayIndex = 0
                   }
                   flashcardQuestion.text = allFlashcard[currentCardDisplayIndex].question
                   flashcardAnswer.text = allFlashcard[currentCardDisplayIndex].answer
                   // Maintenant l'application d'entree
                   flashcardQuestion.startAnimation(rightIn)
               }
               override fun onAnimationRepeat(animation: Animation){}
           })
           // demarrer la 1ere animation sortie
           flashcardQuestion.startAnimation(leftOut)
           if (allFlashcard.size ==0) {
               return@setOnClickListener
           }
           currentCardDisplayIndex++
           if(currentCardDisplayIndex >= allFlashcard.size) {
               Snackbar.make(
                   findViewById<TextView>(R.id.flashcard_question),
                   "Sauvegarde terminee" ,
                   Snackbar.LENGTH_SHORT).show()
               currentCardDisplayIndex = 0
           }
           allFlashcard = flashcardDatabase.getAllCards().toMutableList()
           val (string1, string2) = allFlashcard[currentCardDisplayIndex]
           findViewById<TextView>(R.id.flashcard_answer3).text = string2
           findViewById<TextView>(R.id.flashcard_question).text = string1
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