package com.myproject.app.spotilist.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.myproject.app.spotilist.R
import com.myproject.app.spotilist.adapter.MusicAdapter
import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.databinding.FragmentFavoriteBinding
import com.myproject.app.spotilist.ui.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)

        lifecycleScope.launchWhenStarted {
            launch {
                favoriteViewModel.favorite.collect {
                    if (it.isNotEmpty()) showFavorite(it)
                }
            }
        }
        return binding.root
    }

    private fun showFavorite(music: List<MusicModel>) {
        val list = arrayListOf<MusicModel>()

        music.forEach { musics ->
            val data = MusicModel(
                musics.id,
                musics.album,
                musics.duration,
                musics.imageUrl,
                musics.singers,
                musics.title,
                musics.lyrics,
                false
            )
            list.add(data)
        }

        val musicAdapter = MusicAdapter(requireContext(), list)

        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = musicAdapter
            visibility = View.VISIBLE
            setHasFixedSize(true)
        }
    }
}