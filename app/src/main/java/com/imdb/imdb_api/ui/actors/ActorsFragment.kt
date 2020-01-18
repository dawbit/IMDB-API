package com.imdb.imdb_api.ui.actors


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
//        val textView: TextView = root.findViewById(R.id.textView_database)
//        actorsViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dbHelper = DBHelper(requireContext(), null)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerViewer)
        val adapter = AdapterActors(requireContext(), dbHelper.allActors)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

    }

}
