package com.mbds.example

import com.mbds.example.adapters.Article
import com.mbds.example.adapters.ArticleAdapter
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mbds.example.network.repository.ArticleRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var repo:ArticleRepository = ArticleRepository()

        //recupérer une liste de string depuis les ressources
        val planetes = resources.getStringArray(R.array.planetes)

        val x = repo.list()

        println(x[0])
        println(x[1])
        println(x[2])

        //recupérer l'instance du spinner dans la vue
        val spinner = findViewById<Spinner>(R.id.spinner)
        // instancier l'adapteur
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, x)
        //associer l'adapter au spinner
        spinner.adapter = adapter
        //Listener quand l'utilisateur selectionne un élément
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(baseContext, "Vous n'avez rien selectionné", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onItemSelected(
                adapter: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(
                    baseContext,
                    "Vous avez selectionné ${planetes[position]}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val listArticles = listOf(

        )

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val adapterRecycler = ArticleAdapter(listArticles)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterRecycler

    }
}