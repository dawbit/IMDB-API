package com.imdb.imdb_api.ui.films


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apka6.AdapterFilms
import com.example.apka6.DBHelper
import com.imdb.imdb_api.MainActivity
import com.imdb.imdb_api.R

class FilmsFragment : Fragment() {

    private lateinit var filmsViewModel: FilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        filmsViewModel =
            ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_database, container, false)
//        val textView: TextView = root.findViewById(R.id.textView_database)
//        filmsViewModel.text.observe(this, Observer {
//            textView.text = it
//        })

        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val dbHelper = DBHelper(requireContext(), null)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerViewer)
        val adapter = AdapterFilms(requireContext(), dbHelper.allFilms)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)
    }


}
