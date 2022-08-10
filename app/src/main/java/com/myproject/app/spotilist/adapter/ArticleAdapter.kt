package com.myproject.app.spotilist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myproject.app.spotilist.data.model.ArticleModel
import com.myproject.app.spotilist.databinding.ArticleListBinding

class ArticleAdapter(private val listArticle: List<ArticleModel>) : RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ArticleListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ArticleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = listArticle[position]

        holder.binding.apply {
            Glide.with(ivThumbnail.context)
                .load(article.imageUrl)
                .centerCrop()
                .into(ivThumbnail)
            tvTitle.text = article.title
            tvContent.text = article.content
        }
    }

    override fun getItemCount(): Int = listArticle.size

    interface OnItemClickCallback {
        fun onItemClicked(article: ArticleModel)
    }
}

