package com.example.myapplication.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.interfaces.NewsClickListener
import com.example.myapplication.models.Article
import com.example.myapplication.utils.getNewsFormattedDate
import com.example.myapplication.utils.getProgressDrawable
import com.example.myapplication.utils.loadImage
import kotlinx.android.synthetic.main.layout_news_item.view.*

class NewsAdapter(
    private var news: ArrayList<Article>,
    private var listener: NewsClickListener
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.layout_news_item,
            parent, false
        )
    )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], listener)
    }

    override fun getItemCount() = news.size

    class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var clWrapper = view.clWrapper
        private var ivNews = view.ivNews
        private var tvTitle = view.tvTitle
        private var tvDate = view.tvDate
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(article: Article, listener: NewsClickListener) {
            tvTitle.text = article.title
            tvDate.text = getNewsFormattedDate(article.publishedAt)
            ivNews.loadImage(article.urlToImage, progressDrawable)
            clWrapper.setOnClickListener {
                listener.onNewsClicked(article)
            }

        }
    }

}