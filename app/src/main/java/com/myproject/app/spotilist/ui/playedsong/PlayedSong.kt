package com.myproject.app.spotilist.ui.playedsong

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.databinding.ActivityPlayedSongBinding

class PlayedSong : AppCompatActivity() {

    private var _binding: ActivityPlayedSongBinding?= null
    private val binding get() =_binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlayedSongBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.hide()

        val mediaplayer: MediaPlayer = MediaPlayer.create(this, R.raw.music)

        val seekbar = binding?.sbSong

        seekbar?.progress=0

        seekbar?.max = mediaplayer.duration


        binding?.ivPlay?.setOnClickListener {
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
            }else{
                mediaplayer.pause()
            }
        }

        seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaplayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                TODO("Not yet implemented")
            }
        })
    }
}