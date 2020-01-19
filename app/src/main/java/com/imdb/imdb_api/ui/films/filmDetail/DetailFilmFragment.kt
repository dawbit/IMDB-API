package com.imdb.imdb_api.ui.films.filmDetail


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apka6.AdapterDetail
import com.example.apka6.AdapterDetailDirector

import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.FilmsViewModel
import kotlinx.android.synthetic.main.item_movies_rv.*
import kotlinx.android.synthetic.main.nav_header_main.*

/**
 * A simple [Fragment] subclass.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailFilmFragment : Fragment() {
    private lateinit var filmsViewModel: FilmsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        filmsViewModel =
        ViewModelProviders.of(this).get(FilmsViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

     //   filmsViewModel.gets().observe(this,
       //     Observer<FilmsClass> { t -> Log.d("cipcia", t?.filmTitle.toString()) })

        var bundle = arguments
        Log.d("xdeki",bundle?.getString("movieTitle").toString())
        val restActorsClass = RestActorsClass()
        val restDirectorClass = RestDirectorClass()

        val recyclerActorsView = view!!.findViewById<RecyclerView>(R.id.rv_details_actors)
        val recyclerDirectorView = view!!.findViewById<RecyclerView>(R.id.rv_details_director)

        val adapterActor = AdapterDetail(requireContext())
        val adapterDirector = AdapterDetailDirector(requireContext())

        restActorsClass.searching("batman")
        restDirectorClass.searching("batman")

        restActorsClass.BatmanMutableList.observe(this, Observer { adapterActor.setData(it.Actors) })
        restDirectorClass.BatmanMutableList.observe(this, Observer { adapterDirector.setData(it.Director) })

        recyclerActorsView.adapter = adapterActor
        recyclerDirectorView.adapter = adapterDirector

        recyclerActorsView.layoutManager = LinearLayoutManager(context)
        recyclerDirectorView.layoutManager = LinearLayoutManager(context)


    }









}
