package com.imdb.imdb_api.ui.actors


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apka6.AdapterActors
import com.example.apka6.AdapterFilms
import com.example.apka6.DBHelper

import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.films.FilmsClass

/**
 * A simple [Fragment] subclass.
 */
class ActorsFragment : Fragment() {

    private lateinit var actorsViewModel: ActorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actorsViewModel =
            ViewModelProviders.of(this).get(ActorsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_database, container, false)
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerViewer)
        val adapter = AdapterActors(requireContext(), getAllActorsFromDB(), ::viewActorClassToDataBase)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

    }
    private fun viewActorClassToDataBase(c : ActorsClass){
        actorsViewModel.getClassToMakeChangeInDB(c, requireContext())
    }

    private fun getAllActorsFromDB() :MutableList<ActorsClass>{
        return actorsViewModel.getAllActorsFromDB(requireContext())
    }

}
