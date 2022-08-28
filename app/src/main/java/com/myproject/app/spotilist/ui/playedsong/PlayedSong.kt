package com.myproject.app.spotilist.ui.playedsong

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityPlayedSongBinding
import com.myproject.app.spotilist.utils.Utils.Companion.setImageGlide

class PlayedSong : AppCompatActivity() {

    private var _binding: ActivityPlayedSongBinding?= null
    private val binding get() =_binding
    private lateinit var runnable:Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayedSongBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        initAction()
        getClickedData()
        setToolbar("Played Song")
    }

    private fun initAction() {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.music)

        val seekbar = binding?.sbSong

        seekbar?.progress = 0

        seekbar?.max = mediaPlayer.duration


        binding?.ivPlay?.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                binding?.ivPlay?.setImageResource(R.drawable.ic_pause_foreground)
            } else {
                mediaPlayer.pause()
                binding?.ivPlay?.setImageResource(R.drawable.ic_play_foreground)

            }
        }

        seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekbar?.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer.setOnCompletionListener {
            seekbar?.progress = 0
        }
    }

    private fun getClickedData() {
        val songIntent = intent
        val songImg = songIntent.getStringExtra("img")
        val songTitle = songIntent.getStringExtra("title")
        val songSinger = songIntent.getStringExtra("singer")

        binding?.apply {
            ivAlbumCover.setImageGlide(applicationContext, songImg!!)
            tvSongTitle.text = songTitle
            tvSingers.text = songSinger
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return super.onSupportNavigateUp()
    }

    private fun setToolbar(title: String) {
        setSupportActionBar(binding?.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            this.title = title
        }
    }
}