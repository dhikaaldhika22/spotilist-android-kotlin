package com.myproject.app.spotilist.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.myproject.app.spotilist.adapter.ArticleAdapter
import com.myproject.app.spotilist.data.model.ArticleModel
import com.myproject.app.spotilist.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() =_binding
    private lateinit var database: DatabaseReference
    private lateinit var articleArrayList : ArrayList<ArticleModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        getData()
        return binding?.root
    }

    private fun getData() {
        database = FirebaseDatabase.getInstance("https://spotilist-c3825-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("article")
        articleArrayList = arrayListOf()

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (articleSnapshot in snapshot.children) {
                        val article = articleSnapshot.getValue(ArticleModel::class.java)
                        articleArrayList.add(article!!)
                    }

                    binding?.rvArticleList?.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = ArticleAdapter(articleArrayList)
                        setHasFixedSize(true)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Can't Load Data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun parseArticleDetail() {

    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }

    companion object {

    }
}