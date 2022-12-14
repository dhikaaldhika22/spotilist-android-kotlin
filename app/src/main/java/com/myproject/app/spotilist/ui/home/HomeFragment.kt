package com.myproject.app.spotilist.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.myproject.app.spotilist.adapter.ArticleAdapter
import com.myproject.app.spotilist.data.model.ArticleModel
import com.myproject.app.spotilist.databinding.FragmentHomeBinding
import com.myproject.app.spotilist.ui.login.LoginActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() =_binding
    private lateinit var database: DatabaseReference
    private lateinit var articleArrayList : ArrayList<ArticleModel>
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        auth = Firebase.auth
        getUsername()
        getData()
        logOut()

        return binding?.root
    }

    private fun getUsername() {
        val user = auth.currentUser
        database = FirebaseDatabase.getInstance("https://spotilist-c3825-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("users")
        database.addListenerForSingleValueEvent(object :  ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    if (data.key == user?.uid) {
                        binding?.tvUser?.text = data.getValue(String::class.java)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        binding?.tvUser?.text = user?.displayName
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
                        adapter = ArticleAdapter(context, articleArrayList)
                        setHasFixedSize(true)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Can't Load Data", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun logOut() {
        binding?.btnLogout?.setOnClickListener {
            auth.signOut()
            Toast.makeText(requireContext(), "User Sign Out Successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}