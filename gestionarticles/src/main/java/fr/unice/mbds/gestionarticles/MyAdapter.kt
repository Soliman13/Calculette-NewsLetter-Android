package fr.unice.mbds.gestionarticles

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class MyAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<MyAdapter.ArticleViewHolder>() {

    private val mInflater: LayoutInflater
    private var mArticles = emptyList<Article>() // Cached copy of words

    inner class ArticleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleItemView: TextView
        val descItemView: TextView

        init {
            titleItemView = itemView.findViewById(R.id.tvTitle)
            descItemView = itemView.findViewById(R.id.tvDescription)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = mArticles[position]
        holder.titleItemView.setText(current.title)
        holder.descItemView.setText(current.description)
    }

    internal fun setArticles(articles: List<Article>) {
        mArticles = articles
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mArticles.size
    }
}