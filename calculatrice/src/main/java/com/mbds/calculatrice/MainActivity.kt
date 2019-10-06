package com.mbds.calculatrice

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {

    private lateinit var resultat:TextView
    private lateinit var historique:TextView
    private var first_term = 0f
    private var operation = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultat = findViewById(R.id.tv_resultat)
        historique = findViewById(R.id.tv_contenu_historique)
    }

    fun operation(view: View) {
        if(resultat.text.toString().toFloat() == 0f) return

        when(view.id){
            R.id.btn_addition -> {
                ope()
                operation = "+"
            }
            R.id.btn_soustraction -> {
                ope()
                operation = "-"
            }
            R.id.btn_multiplication -> {
                ope()
                operation = "*"
            }
            R.id.btn_division -> {
                ope()
                operation = "/"
            }
            R.id.btn_clear -> {
                resultat.text = "0"
                first_term = 0f
                operation = ""
            }
            R.id.btn_negatif_positif -> {
                if(resultat.text.startsWith("-")){
                    val x = resultat.text.toString().toFloat().absoluteValue
                    resultat.text = x.toString()
                }
                else{
                    resultat.text = "-".plus(resultat.text)
                }
            }
            R.id.btn_clear_histo -> historique.text = " "
            R.id.btn_pourcentage -> resultat.text = (resultat.text.toString().toFloat()/100).toString()
            R.id.btn_resultat -> {
                result()
            }
        }
    }

    private fun result() {
        historique.append("$first_term $operation ${resultat.text.toString().toFloat()}\n")
        when(operation){
            "+" -> resultat.text = (first_term + resultat.text.toString().toFloat()).toString()
            "-" -> resultat.text = (first_term - resultat.text.toString().toFloat()).toString()
            "*" -> resultat.text = (first_term * resultat.text.toString().toFloat()).toString()
            "/" -> resultat.text = (first_term / resultat.text.toString().toFloat()).toString()
        }
        first_term = resultat.text.toString().toFloat()
    }

    private fun ope() {
        if(first_term != 0f && first_term.toString() != resultat.text.toString()){
//            historique.append("$first_term $operation ${resultat.text}\n")
            result()
            return
        }
        first_term = resultat.text.toString().toFloat()
        resultat.text = "0"
    }

    fun addNum(view: View) {
        if(resultat.text.startsWith('0')){
            resultat.text = ""
        }
        when(view.id){
            R.id.btn_num0 -> resultat.append("0")
            R.id.btn_num1 -> resultat.append("1")
            R.id.btn_num2 -> resultat.append("2")
            R.id.btn_num3 -> resultat.append("3")
            R.id.btn_num4 -> resultat.append("4")
            R.id.btn_num5 -> resultat.append("5")
            R.id.btn_num6 -> resultat.append("6")
            R.id.btn_num7 -> resultat.append("7")
            R.id.btn_num8 -> resultat.append("8")
            R.id.btn_num9 -> resultat.append("9")
            R.id.btn_point ->
                if (! (resultat.text.contains(".")) ){
                    resultat.append(".")
                }
        }
    }
}
