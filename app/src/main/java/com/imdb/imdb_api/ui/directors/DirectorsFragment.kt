package com.imdb.imdb_api.ui.directors


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apka6.AdapterDirectors
import com.example.apka6.AdapterFilms
import com.example.apka6.DBHelper

import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.films.FilmsClass

/**
 * A simple [Fragment] subclass.
 */
class DirectorsFragment : Fragment() {
    private lateinit var directorsViewModel: DirectorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        directorsViewModel =
            ViewModelProviders.of(this).get(DirectorsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_database, container, false)
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerViewer)
        val adapter = AdapterDirectors(requireContext(), getAllActorsFromDB() , ::viewDirectorClassToDataBase)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)
    }
    fun viewDirectorClassToDataBase(c : DirectorsClass){
        directorsViewModel.getClassToMakeChangeInDB(c, requireContext())
    }
    private fun getAllActorsFromDB(): MutableList<DirectorsClass> {
        return directorsViewModel.getAllActorsFromDB(requireContext())
    }
}
