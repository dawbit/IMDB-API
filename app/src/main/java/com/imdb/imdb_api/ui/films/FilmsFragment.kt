package com.imdb.imdb_api.ui.films


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apka6.AdapterFilms
import com.example.apka6.DBHelper
import com.imdb.imdb_api.MainActivity
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmFragment

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

        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerViewer)
        val adapter = AdapterFilms(requireContext(), getAllFilmsFromDB(), ::viewFilmClassToDataBase, ::changeFragmentToFilmDetail)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun viewFilmClassToDataBase(c : FilmsClass){
        filmsViewModel.getClassToMakeChangeInDB(c, requireContext())
    }
    private fun changeFragmentToFilmDetail (c : FilmsClass){
//        val intent = Intent(context as MainActivity, DetailFilmFragment::class.java)
//        intent.putExtra("movieTitle", c.filmTitle)
//        intent.putExtra("movieYear", c.filmYear)                          TO TRZEBA JESZCZE OGARNAC
//        intent.putExtra("moviePoster", c.filmPoster)


        (context as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.nav_film_detail).apply {
            (context as MainActivity).findNavController(R.id.nav_host_fragment)
                .currentDestination?.label = c.filmTitle
            (context as MainActivity).onBackPressed()
            (context as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.nav_film_detail)

        }
    }
    private fun getAllFilmsFromDB() : MutableList<FilmsClass>{
        return filmsViewModel.getAllFilmsFromDB(requireContext())
    }


}
