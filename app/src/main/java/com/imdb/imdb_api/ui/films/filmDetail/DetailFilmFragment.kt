package com.imdb.imdb_api.ui.films.filmDetail


import android.content.Intent
import android.net.Uri
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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.apka6.AdapterDetail
import com.example.apka6.AdapterDetailDirector

import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.FilmsViewModel
import kotlinx.android.synthetic.main.fragment_detail_film.*
import kotlinx.android.synthetic.main.item_movies_rv.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.net.URL
import kotlinx.android.synthetic.main.fragment_detail_film.imageDetailMovie as imageDetailMovie1

/**
 * A simple [Fragment] subclass.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")
class DetailFilmFragment : Fragment() {
    private lateinit var filmsViewModel: FilmsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        filmsViewModel =
        ViewModelProviders.of(this).get(FilmsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_detail_film, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        filmsViewModel = activity!!.run{ViewModelProviders.of(this).get(filmsViewModel::class.java)}


        val recyclerActorsView = view!!.findViewById<RecyclerView>(R.id.rv_details_actors)
        val recyclerDirectorView = view!!.findViewById<RecyclerView>(R.id.rv_details_director)

        val adapterActor = AdapterDetail(requireContext(), ::viewActorClassToDataBase, ::checkifActorExistsInDB)
        val adapterDirector = AdapterDetailDirector(requireContext(), ::viewDirectorClassToDataBase, ::checkifDirectorExistsInDB)


        filmsViewModel.batmanMutableActorList.observe(this, Observer { adapterActor.setData(it.Actors)
           // 2.6/2,4/2.5  zadanie2.text=it.    Runtime/Plot/costam
        })
        filmsViewModel.batmanMutableDirectorList.observe(this, Observer { adapterDirector.setData(it.Director)})


        filmsViewModel.filmClass.observe(this, Observer {
            if(it.filmPoster!="N/A") {
            val url = URL(it.filmPoster)
            Glide.with(requireContext())
                .load(url)
                .into(imageDetailMovie)
        }
        })
//region 2.1
//        imdbButton.setOnClickListener(){
//            val openURL = Intent(Intent.ACTION_VIEW)
//            openURL.data = Uri.parse("https://www.imdb.com/title/" + filmsViewModel.filmClass.value?.imdbID + "/")
//            startActivity(openURL)
//        }
//endregion

//region 1.2
//        filmsViewModel.filmClass.observe(this, Observer {
//            if(it.filmPoster!="N/A") {
//                val url = URL(it.filmPoster)
//                Glide.with(requireContext())
//                    .load(url)
//                    .into(imageDetailMovie)
//
//            }
//            adapterActor.imageForActors(it.filmPoster) //1.2
//        })
//endregion

//region 1.1
//        filmsViewModel.filmClass.observe(this, Observer {
//            titleImage.text = it.filmTitle
//        })
//endregion
        recyclerActorsView.adapter = adapterActor
        recyclerDirectorView.adapter = adapterDirector

        recyclerActorsView.layoutManager = LinearLayoutManager(context)
        recyclerDirectorView.layoutManager = LinearLayoutManager(context)


    }

    private fun viewActorClassToDataBase(c : DetailFilmActorsClass){
        filmsViewModel.checkActorsFromDetail(c, requireContext())
    }
    private fun viewDirectorClassToDataBase(c : DetailFilmDirectorClass){
        filmsViewModel.checkDirectorsFromDetail(c, requireContext())
    }
    private fun checkifActorExistsInDB(c:DetailFilmActorsClass):Int{
        return filmsViewModel.checkifActorExistsInDB(c,requireContext())
    }
    private fun checkifDirectorExistsInDB(c : DetailFilmDirectorClass):Int{
        return filmsViewModel.checkifDirectorExistsInDB(c,requireContext())
    }








}
