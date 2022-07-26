package com.rochim.finalprojectschoters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rochim.finalprojectschoters.databinding.ItemNewsBinding
import com.rochim.finalprojectschoters.models.Article

class NewsAdapter(private val newsList: List<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    inner class NewsViewHolder(val itemBinding: ItemNewsBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(itemBinding.root){
        fun bindItem(news: Article){
            itemBinding.tvName.text = news.source?.name
            itemBinding.tvAuthor.text = news.author
            itemBinding.tvTitle.text = news.title
            itemBinding.tvDesc.text = news.description
            itemBinding.tvPublished.text = news.publishedAt
            Glide.with(itemView).load(news.urlToImage).into(itemBinding.ivGambar)
        }
        init {
            itemBinding.cvNews.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false), mListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.bindItem(news)
    }

    override fun getItemCount(): Int = newsList.size

}