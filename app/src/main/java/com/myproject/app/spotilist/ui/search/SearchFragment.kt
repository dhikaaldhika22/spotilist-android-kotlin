package com.myproject.app.spotilist.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.myproject.app.spotilist.adapter.MusicAdapter
import com.myproject.app.spotilist.data.model.MusicModel
import com.myproject.app.spotilist.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var database: DatabaseReference
    private lateinit var musicArrayList : ArrayList<MusicModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        getData()

        return binding?.root
    }

    private fun getData() {
        database = FirebaseDatabase.getInstance("https://spotilist-c3825-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("music")
        musicArrayList = arrayListOf()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (musicSnapshot in snapshot.children) {
                        val music = musicSnapshot.getValue(MusicModel::class.java)
                        musicArrayList.add(music!!)
                    }

                    binding?.rvSongsList?.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = MusicAdapter(musicArrayList)
                        setHasFixedSize(true)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Can't load Data", Toast.LENGTH_SHORT).show()
            }
        })
    }

}