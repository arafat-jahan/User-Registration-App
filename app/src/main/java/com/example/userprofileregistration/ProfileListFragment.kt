package com.example.userprofileregistration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileListFragment : Fragment() {

    private lateinit var profileAdapter: ProfileAdapter
    private lateinit var profileViewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_list, container, false)

        // Initialize RecyclerView and ViewModel
        val recyclerView = view.findViewById<RecyclerView>(R.id.profileRecyclerView)
        profileAdapter = ProfileAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = profileAdapter

        profileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        profileViewModel.getUserProfiles().observe(viewLifecycleOwner, Observer { profiles ->
            profileAdapter.submitList(profiles)
        })

        return view
    }
}
