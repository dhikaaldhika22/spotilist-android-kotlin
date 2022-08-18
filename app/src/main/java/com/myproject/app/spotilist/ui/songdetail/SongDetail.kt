package com.myproject.app.spotilist.ui.songdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myproject.app.spotilist.databinding.ActivitySongDetailBinding
import com.myproject.app.spotilist.utils.Utils.Companion.setImageGlide

class SongDetail : AppCompatActivity() {
    private lateinit var binding: ActivitySongDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar("Detail")
        getDetailData()
    }

    private fun getDetailData() {
        val songIntent = intent
        val songImg = songIntent.getStringExtra("img")
        val songTitle = songIntent.getStringExtra("title")
        val songSinger = songIntent.getStringExtra("singer")
        val songLyric = songIntent.getStringExtra("lyrics")

        binding.apply {
            ivImage.setImageGlide(applicationContext, songImg!!)
            tvSongTitle.text = songTitle
            tvSingers.text = songSinger
            tvSongsLyrics.text = songLyric
        }
    }

    private fun setToolbar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            this.title = title
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }
}