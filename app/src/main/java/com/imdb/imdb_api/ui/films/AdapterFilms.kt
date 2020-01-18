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
import java.net.URL

class AdapterFilms(val context: Context, val filmlist: MutableList<FilmsClass>): RecyclerView.Adapter<AdapterFilms.ViewHolder>() {
    init {
        filmlist.sortBy { it.filmID }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dbHandler = DBHelper(context, null)
        val fil = filmlist[position]
        holder.filmTitleTextView.text = fil.filmTitle
        holder.filmYearTextView.text = fil.filmYear
        holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)

        if(fil.filmPoster!="N/A") {
            val url = URL(fil.filmPoster)
            Glide.with(context)
                .load(url)
                .into(holder.moviePosterImageView)
        }
        holder.favouriteButton.setOnClickListener{
            if(dbHandler.checkFilm(fil.filmTitle, fil.filmYear)==1) {
                dbHandler.delFilm(fil.filmTitle, fil.filmYear)
                holder.favouriteButton.setImageResource(R.drawable.ic_favourite_false)
            }
            else {
                var helper = FilmsClass(fil.filmTitle, fil.filmYear, fil.filmPoster)
                dbHandler.addFilm(helper)
                holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
            }
        }
        holder.itemView.setOnClickListener{

            (it.context as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.nav_film_detail).apply {
                (it.context as MainActivity).findNavController(R.id.nav_host_fragment)
                    .currentDestination?.label = fil.filmTitle
                (it.context as MainActivity).onBackPressed()
                (it.context as MainActivity).findNavController(R.id.nav_host_fragment).navigate(R.id.nav_film_detail)
            }
            val intent = Intent(context,DetailFilmFragment::class.java)
            intent.putExtra("movieTitle", fil.filmTitle)
            intent.putExtra("movieYear", fil.filmYear)
            intent.putExtra("moviePoster", fil.filmPoster)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val filmTitleTextView=view.findViewById<TextView>(R.id.titleMovie)
        val filmYearTextView=view.findViewById<TextView>(R.id.yearMovie)
        val moviePosterImageView = view.findViewById<ImageView>(R.id.imageDetailMovie)
        val favouriteButton = view.findViewById<ImageButton>(R.id.movieIsFavourite)
    }


}