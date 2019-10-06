package com.midotechno.gestionarticlenewsapi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.api.load
import com.midotechno.gestionarticlenewsapi.Entities.Article
import com.midotechno.gestionarticlenewsapi.R


class ArticleAdapter(private val dataset: List<Article>) :

    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {

        lateinit var image : ImageView

        fun bind(item: Article) {

            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            val txtDesc = root.findViewById<TextView>(R.id.article_description)
            image = root.findViewById<ImageView>(R.id.imageView)
            var txtdate = root.findViewById<TextView>(R.id.date_publication)
            var txtlien = root.findViewById<TextView>(R.id.voirPlus)

            txtTitle.text = item.title
            txtDesc.text = item.description
            txtdate.text= "Ecrit: " + item.publishedAt
            txtlien.text = "Plus d'info: " + item.url

            // chargement de l'image avec coil
            chargerImageURL(item.urlToImage)
        }

        private fun chargerImageURL(url : String) {

            // voir le cas ou URL image est null faire image par default

//          val url = URL(urlImage)
//          val img = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            // verification si l'article contient une image
            if (url.isNullOrEmpty()){
                image.load(R.mipmap.mbds_foreground) // load normal image si l'article ne contient pas d'image
            }else{
                image.load(url)  // load image depuis url
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount() = dataset.size


}