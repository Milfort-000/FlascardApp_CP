package com.example.flashcarrapp_cp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.View
import android.view.View.INVISIBLE
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar


class AjouterActiviteCarte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajouter_activite_carte)




        val questionField = findViewById<EditText>(R.id.questionTextField)
            val answerField = findViewById<EditText>(R.id.answerTextField)
           // val saveButton = findViewById<Button>(R.id.saveButton)
            val save_icon = findViewById<ImageView>(R.id.save_icon)
            val cercle_annuler_icon = findViewById<ImageView>(R.id.cercle_annuler_icon)
            /*val cercle_icon_plus = findViewById<ImageView>(R.id.cercle_icon_plus)*/


      /*  saveButton.setOnClickListener {
                val data = Intent()
                data.putExtra("string1", questionField.text.toString())
                data.putExtra("string2", answerField.text.toString())
                setResult(RESULT_OK, data)
                finish()
            }
            */


        // Clique sur "Annuler" → retour à l'état initial
        cercle_annuler_icon.setOnClickListener {
            finish()
            /*cercle_annuler_icon.visibility = INVISIBLE*/
            /*cercle_icon_plus.visibility = View.VISIBLE*/
        }


        save_icon.setOnClickListener {
            val data = Intent()
            data.putExtra("string1", questionField.text.toString())
            data.putExtra("string2", answerField.text.toString())
            setResult(RESULT_OK, data)
            finish()
        }





        // Bouton Annuler -> ferme l’activité
        cercle_annuler_icon.setOnClickListener {
            finish()
        }


            /*if (questionField.isEmpty() || answerField.isEmpty()) {
                Snackbar.make(saveButton, "Veuillez remplir les deux champs", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/
      /* if (questionField.isEmpty() || answerField.isEmpty())  {
            Snackbar.make(
                findViewById(R.id.flashcard_question),
                "Veuillez",
                Snackbar.LENGTH_SHORT
            ).show()
           return@setOnClickListener
        } */











        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}