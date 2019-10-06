package com.mbds.calculatrice

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import kotlin.math.absoluteValue
import android.os.Looper.getMainLooper
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var resultat:TextView
    private lateinit var historique:TextView
    private var premier_terme = 0f
    private var second_terme = 0f
    private var apresResultat = false
    private var operation = ' '

    private lateinit var db : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultat = findViewById(R.id.tv_resultat)
        historique = findViewById(R.id.tv_contenu_historique)

        db = AppDBProvider.getInstance(this)
    }

    fun operation(view: View) {
        when(view.id){
            R.id.btn_addition -> {
                ope('+')
            }
            R.id.btn_soustraction -> {
                ope('-')
            }
            R.id.btn_multiplication -> {
                ope('*')
            }
            R.id.btn_division -> {
                ope('/')
            }
            R.id.btn_clear -> {
                resultat.text = "0"
                premier_terme = 0f
                second_terme = 0f
                operation = ' '
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
            R.id.btn_pourcentage -> if(!resultat.text.endsWith(".")) resultat.text = (resultat.text.toString().toFloat()/100).toString()
            R.id.btn_resultat -> {
                result()
            }
        }
    }

    private fun result() {
        if (operation.equals(' ') || resultat.text.endsWith(".")) return

        var addTohistorique = ""
        if(premier_terme == resultat.text.toString().toFloat() && second_terme != 0f){ // cas de double égal
            addTohistorique = addTohistorique +"$premier_terme $operation $second_terme = "
            when(operation){
                '+' -> resultat.text = (premier_terme + second_terme).toString()
                '-' -> resultat.text = (premier_terme - second_terme).toString()
                '*' -> resultat.text = (premier_terme * second_terme).toString()
                '/' -> resultat.text = (premier_terme / second_terme).toString()
            }
            addTohistorique = addTohistorique + "${resultat.text}\n"
        }
        else{
            second_terme = resultat.text.toString().toFloat()
            addTohistorique = addTohistorique + "$premier_terme $operation ${resultat.text.toString().toFloat()} = "
            when(operation){
                '+' -> resultat.text = (premier_terme + resultat.text.toString().toFloat()).toString()
                '-' -> resultat.text = (premier_terme - resultat.text.toString().toFloat()).toString()
                '*' -> resultat.text = (premier_terme * resultat.text.toString().toFloat()).toString()
                '/' -> resultat.text = (premier_terme / resultat.text.toString().toFloat()).toString()
            }
            addTohistorique = addTohistorique + "${resultat.text}\n"
        }
        premier_terme = resultat.text.toString().toFloat()
        apresResultat = true


        historique.append(addTohistorique)
        var th = Thread(Runnable { db.getHistoriqueDao().insert(Historique(1,addTohistorique)) })
        th.start()


    }

    private fun ope(op: Char) {
        if(resultat.text.endsWith(".")) return
        else operation = op

        if(premier_terme != 0f && premier_terme.toString() != resultat.text.toString()){
            result()
            return
        }
        premier_terme = resultat.text.toString().toFloat()
        resultat.text = "0"
        apresResultat = false
    }

    fun addNum(view: View) {
        if(resultat.text.startsWith('0')){
            resultat.text = ""
        }
        if(apresResultat){
            resultat.text = ""
            premier_terme = 0f
            second_terme = 0f
            operation = ' '
            apresResultat = false
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
                if (!resultat.text.contains(".")){
                    resultat.append(".")
                }
        }
    }
}
