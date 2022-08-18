package com.myproject.app.spotilist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myproject.app.spotilist.data.model.ArticleModel
import com.myproject.app.spotilist.databinding.ArticleListBinding
import com.myproject.app.spotilist.ui.articledetail.ArticleDetailActivity

class ArticleAdapter(var c: Context, private val listArticle: List<ArticleModel>) : RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {
    class ListViewHolder(var binding: ArticleListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ArticleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = listArticle[position]
        val detailArticle = listArticle[position]


        holder.binding.apply {
            Glide.with(ivThumbnail.context)
                .load(article.imageUrl)
                .centerCrop()
                .into(ivThumbnail)
            tvTitle.text = article.title
            tvContent.text = article.content

            holder.itemView.setOnClickListener {
                val img = detailArticle.imageUrl
                val title = detailArticle.title
                val content = detailArticle.content
                val intent = Intent(c, ArticleDetailActivity::class.java)
                intent.putExtra("img", img)
                intent.putExtra("title", title)
                intent.putExtra("content", content)
                c.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = listArticle.size
}

