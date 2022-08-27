package com.myproject.app.spotilist.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.databinding.MusicListBinding
import com.myproject.app.spotilist.ui.playedsong.PlayedSong
import com.myproject.app.spotilist.ui.songdetail.SongDetail

class MusicAdapter(var c: Context, private val listMusic: List<MusicModel>) : RecyclerView.Adapter<MusicAdapter.ListViewHolder>() {
    class ListViewHolder (var binding: MusicListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: MusicListBinding = MusicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val music = listMusic[position]
        val detailMusic = listMusic[position]

        holder.binding.apply {
            Glide.with(ivThumbnail.context)
                .load(music.imageUrl)
                .centerCrop()
                .into(ivThumbnail)

            tvTitle.text = music.title
            tvSingers.text = music.singers
            tvAlbum.text = music.album
            tvDuration.text = music.duration.toString()
        }

        holder.itemView.setOnClickListener {
            val id = detailMusic.id
            val img = detailMusic.imageUrl
            val title = detailMusic.title
            val singer = detailMusic.singers
            var lyrics = detailMusic.lyrics
            if (lyrics.contains("_n")) {
                val newLyric: String = lyrics.replace("_n", "\n")
                lyrics = newLyric
            }
            val intent = Intent(c, SongDetail::class.java)
            intent.putExtra("id", id)
            intent.putExtra("img", img)
            intent.putExtra("title", title)
            intent.putExtra("singer", singer)
            intent.putExtra("lyrics", lyrics)
            c.startActivity(intent)

            /*val intent2 = Intent(c, PlayedSong::class.java)
            intent2.putExtra("img", img)
            intent2.putExtra("title", title)
            intent2.putExtra("singer", singer)
            intent2.putExtra("lyrics", lyrics)
            c.startActivity(intent2)*/
        }
    }

    override fun getItemCount(): Int = listMusic.size
}