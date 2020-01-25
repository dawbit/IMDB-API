package com.example.apka6

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.imdb.imdb_api.MainActivity
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.FilmsFragment
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_movies_rv.view.*
import java.net.URL

@Suppress("DEPRECATION")
class AdapterFilms(val context: Context, val filmlist: MutableList<FilmsClass>, viewFilmClassToDataBase: (c:FilmsClass) -> Unit
                   , changeFragmentToFilmDetail: (c:FilmsClass) -> Unit): RecyclerView.Adapter<AdapterFilms.ViewHolder>() {
    private var viewFilmClassToDataBase : ((FilmsClass) -> Unit)? = null
    private var changeFragmentToFilmDetail : ((FilmsClass) -> Unit)? = null
    init {
        filmlist.sortBy { it.filmID }
        this.viewFilmClassToDataBase=viewFilmClassToDataBase
        this.changeFragmentToFilmDetail=changeFragmentToFilmDetail
    }

//region 1.5
//    init {
//        filmlist.sortBy { it.filmTitle }
//        this.viewFilmClassToDataBase=viewFilmClassToDataBase
//        this.changeFragmentToFilmDetail=changeFragmentToFilmDetail
//    }
//endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fil = filmlist[position]
        holder.filmTitleTextView.text = fil.filmTitle
        holder.filmYearTextView.text = fil.filmYear
        //holder.filmRate.text=fil.imdbID 2.2

        if(fil.filmPoster!="N/A") {
            val url = URL(fil.filmPoster)
            Glide.with(context)
                .load(url)
                .into(holder.moviePosterImageView)
        }

        holder.favouriteButtonTrue.isVisible = true
        holder.favouriteButton.isVisible = false

        holder.favouriteButtonTrue.setOnClickListener{
            viewFilmClassToDataBase?.let { it1 -> it1(fil) }
            holder.favouriteButton.isVisible = true
            holder.favouriteButtonTrue.isVisible =false
        }
        holder.favouriteButton.setOnClickListener{
            viewFilmClassToDataBase?.let { it1 -> it1(fil) }
            holder.favouriteButtonTrue.isVisible = true
            holder.favouriteButton.isVisible =false
        }
        holder.itemView.setOnClickListener{
            changeFragmentToFilmDetail?.let { it1 -> it1(fil) }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val filmTitleTextView=view.findViewById<TextView>(R.id.titleMovie)
        val filmYearTextView=view.findViewById<TextView>(R.id.yearMovie)
        val moviePosterImageView = view.findViewById<ImageView>(R.id.imageDetailMovie)
        val favouriteButton = view.findViewById<ImageButton>(R.id.movieIsFavourite)
        val favouriteButtonTrue = view.findViewById<ImageButton>(R.id.movieIsFavouriteTrue)
       // val filmRate = view.findViewById<TextView>(R.id.rateMovie) //2.2
        //val filmTypeTextView=view.findViewById<TextView>(R.id.filmType) // 2.5
    }


}