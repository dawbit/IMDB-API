package com.example.apka6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.search.SearchFilmApi
import kotlinx.android.synthetic.main.item_movies_rv.view.*
import java.net.URL
import java.util.ArrayList
import com.bumptech.glide.Glide
import com.imdb.imdb_api.ui.films.FilmsClass

class AdapterSearch(var context: Context) : RecyclerView.Adapter<AdapterSearch.ViewHolder>() {

    private var listInAdapter = ArrayList<SearchFilmApi>()

    fun setMovies(list: ArrayList<SearchFilmApi>){
        listInAdapter= list
        notifyDataSetChanged()
    }



    inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val movieTitleTextView = view.titleMovie!!
        val movieYearTextView = view.yearMovie!!
        val moviePosterImageView = view.imageDetailMovie!!
        val favouriteButton = view.movieIsFavourite
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listInAdapter.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val act = listInAdapter[position]

        var db = DBHelper(context,null)
        if(db.checkFilm(act.Title, act.Year)==1){
            holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
        }
        holder.movieTitleTextView.text=act.Title
        holder.movieYearTextView.text=act.Year
        if(act.Poster!="N/A") {
            val url = URL(act.Poster)
            Glide.with(context)
                .load(url)
                .into(holder.moviePosterImageView)
        }

    holder.favouriteButton.setOnClickListener{
        if(db.checkFilm(act.Title, act.Year)==1) {
            db.delFilm(act.Title, act.Year)
            holder.favouriteButton.setImageResource(R.drawable.ic_favourite_false)
        }
        else {
            var helper = FilmsClass(act.Title, act.Year, act.Poster)
            db.addFilm(helper)
            holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
        }
    }

    }

    fun itemAmount(){
        listInAdapter = ArrayList()
        notifyDataSetChanged()
    }

}
