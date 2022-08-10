package com.myproject.app.spotilist.ui.articledetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.data.model.ArticleModel
import com.myproject.app.spotilist.databinding.ActivityArticleDetailBinding
import com.myproject.app.spotilist.utils.Utils.Companion.setImageGlide

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding
    private var articleDetail: ArticleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(getString(R.string.article))
        articleDetail?.let { onArticleDetail(it) }
    }

    private fun setToolbar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            this.title = title
        }
    }

    private fun onArticleDetail(article: ArticleModel) {
        parseArticleDetail(article)

        val articleModel = ArticleModel(article.title, article.imageUrl, article.content)
        articleDetail = articleModel
    }


    private fun parseArticleDetail(article: ArticleModel) {
        binding.apply {
            ivImage.setImageGlide(this@ArticleDetailActivity, article.imageUrl)
            tvArticleTitle.text = article.title
            tvArticleContent.text = article.content
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}