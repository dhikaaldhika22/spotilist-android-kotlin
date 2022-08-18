package com.myproject.app.spotilist.ui.articledetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityArticleDetailBinding
import com.myproject.app.spotilist.utils.Utils.Companion.setImageGlide

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar(getString(R.string.article))
        getDetailArticle()
    }

    private fun setToolbar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            this.title = title
        }
    }

    private fun getDetailArticle() {
        val articleIntent = intent
        val articleImg = articleIntent.getStringExtra("img")
        val articleTitle = articleIntent.getStringExtra("title")
        val articleContent = articleIntent.getStringExtra("content")

        binding.apply {
            ivImage.setImageGlide(applicationContext, articleImg!!)
            tvArticleTitle.text = articleTitle
            tvArticleContent.text = articleContent
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }
}