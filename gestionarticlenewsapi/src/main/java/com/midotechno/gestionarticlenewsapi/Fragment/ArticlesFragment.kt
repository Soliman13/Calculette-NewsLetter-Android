package com.midotechno.gestionarticlenewsapi.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.midotechno.gestionarticlenewsapi.Adapter.ArticleAdapter
import com.midotechno.gestionarticlenewsapi.Entities.Article
import com.midotechno.gestionarticlenewsapi.Network.ArticlePository
import com.midotechno.gestionarticlenewsapi.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesFragment : Fragment() {

    init {

    }

    private val repository = ArticlePository()
    private lateinit var v : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_articles, container, false)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch {
            getData()
        }
    }

    //S'execute dans un thread secondeaire
    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.list()

            if (result.articles == null || result.status == "Nok"){
//                Toast.makeText(context,"Erreur de chargement données",Toast.LENGTH_SHORT)
            }else{
                bindData(result.articles)
            }
        }
    }


    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {

            // partie recycler view
            //instance du recycler
            var recyclerView : RecyclerView = v.findViewById(R.id.reycler_view)

            //créer une instance de l'adapteur
            var adapterRecycler = ArticleAdapter(result)

            //définir l'orientation des élements (vertical)
            recyclerView.layoutManager = LinearLayoutManager(v.context)

            //associer l'adapter à la recyclerview
            recyclerView.adapter = adapterRecycler

        }
    }



}
