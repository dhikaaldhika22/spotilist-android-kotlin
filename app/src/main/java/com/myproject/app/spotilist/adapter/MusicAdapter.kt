package com.myproject.app.spotilist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.databinding.MusicListBinding

class MusicAdapter(private val listMusic: List<MusicModel>) : RecyclerView.Adapter<MusicAdapter.ListViewHolder>() {
    class ListViewHolder (var binding: MusicListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding: MusicListBinding = MusicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val music = listMusic[position]

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
    }

    override fun getItemCount(): Int = listMusic.size


}