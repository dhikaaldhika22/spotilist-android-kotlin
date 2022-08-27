package com.myproject.app.spotilist.ui.songdetail

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.databinding.ActivitySongDetailBinding
import com.myproject.app.spotilist.ui.ViewModelFactory
import com.myproject.app.spotilist.ui.playedsong.PlayedSong
import com.myproject.app.spotilist.utils.Utils.Companion.setImageGlide
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SongDetail : AppCompatActivity() {
    private var _binding: ActivitySongDetailBinding? = null
    private val binding get() = _binding!!

    private var isFavorite: Boolean? = null
    private var songDetailViewModel: MusicModel? = null

    private var id: Int? = null

    private val detailViewModel: SongDetailViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySongDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar("Detail")
        getDetailData()
        favoriteAction()

        val songIntent = intent
       // id = intent?.extras?.getString("id") as Int
        id = songIntent.getStringExtra("id")?.toInt()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    id?.let {
                        detailViewModel.isFavorite(it).collect { state ->
                            isFavorite(state)
                            isFavorite = state
                        }
                    }
                }
            }
        }
    }

    private fun getDetailData() {
        val songIntent = intent
        songIntent.getStringExtra("id")
        val songImg = songIntent.getStringExtra("img")
        val songTitle = songIntent.getStringExtra("title")
        val songSinger = songIntent.getStringExtra("singer")
        val songLyric = songIntent.getStringExtra("lyrics")

        binding.apply {
            ivImage.setImageGlide(applicationContext, songImg!!)
            tvSongTitle.text = songTitle
            tvSingers.text = songSinger
            tvSongsLyrics.text = songLyric?.replace("_n", "\n")
            btnPlaySong.setOnClickListener {
                val intent = Intent(this@SongDetail, PlayedSong::class.java)
                intent.putExtra("img", songImg)
                intent.putExtra("title", songTitle)
                intent.putExtra("singer", songSinger)
                startActivity(intent)
            }
        }
    }

    private fun isFavorite(condition: Boolean) {
        binding.apply {
            if (condition) {
                ibFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                ibFavorite.setImageResource(R.drawable.ic_baseline_favorite_border)
            }
        }
    }

    private fun favoriteAction() {
        binding.apply {
            ibFavorite.setOnClickListener {
                if (isFavorite == false) {
                    songDetailViewModel?.let {
                        detailViewModel.saveToFavorite(it)
                    }
                    isFavorite(true)
                    Toast.makeText(this@SongDetail, "Berhasil ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
                } else {
                    songDetailViewModel?.let {
                        detailViewModel.deleteFavorite(it)
                    }
                    isFavorite(false)
                    Toast.makeText(this@SongDetail, "Berhasil dihapus dari favorit", Toast.LENGTH_SHORT).show()
                }
            }
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

    override fun onDestroy() {
        id = null
        isFavorite = null

        super.onDestroy()
    }

    companion object {
        const val EXTRA_ID = "id"
    }
}

