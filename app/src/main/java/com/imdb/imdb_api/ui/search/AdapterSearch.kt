package com.example.apka6

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.search.SearchFilmApi
import kotlinx.android.synthetic.main.item_movies_rv.view.*
import java.net.URL
import java.util.ArrayList
import com.bumptech.glide.Glide
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.search.SearchViewModel

@Suppress("DEPRECATION")
class AdapterSearch(var context: Context, viewFilmClassToDataBase: (c:SearchFilmApi) -> Int, viewFilmAddDelToDataBase: (c:SearchFilmApi) -> Unit) : RecyclerView.Adapter<AdapterSearch.ViewHolder>() {


    private var viewFilmClassToDataBase: ((SearchFilmApi) -> Int)? = null
    private var viewFilmAddDelToDataBase: ((SearchFilmApi) -> Unit)? = null
    private var listInAdapter = ArrayList<SearchFilmApi>()

    init{
        this.viewFilmClassToDataBase = viewFilmClassToDataBase
        this.viewFilmAddDelToDataBase=viewFilmAddDelToDataBase
    }

    fun setMovies(list: ArrayList<SearchFilmApi>){
        listInAdapter= list
        notifyDataSetChanged()
    }

//region 1.5
//    fun setMovies(list: ArrayList<SearchFilmApi>){
//        listInAdapter= list
//        listInAdapter.sortBy { it.Title }
//        notifyDataSetChanged()
//    }
//endregion
    inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val movieTitleTextView = view.titleMovie!!
        val movieYearTextView = view.yearMovie!!
        val moviePosterImageView = view.imageDetailMovie!!
        val favouriteButton = view.movieIsFavourite
        val favouriteButtonTrue = view.movieIsFavouriteTrue
       // val movieTypeTextView = view.filmType!!
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

        if(viewFilmClassToDataBase?.let { it(act) } ==1){
            holder.favouriteButtonTrue.isVisible=true
            holder.favouriteButton.isVisible=false
        }
        else{
            holder.favouriteButtonTrue.isVisible=false
            holder.favouriteButton.isVisible=true
        }

     //   holder.movieTypeTextView.text=act.Type //2.7
        holder.movieTitleTextView.text=act.Title
        holder.movieYearTextView.text=act.Year

        if(act.Poster!="N/A") {
            val url = URL(act.Poster)
            Glide.with(context)
                .load(url)
                .into(holder.moviePosterImageView)
        }

        holder.favouriteButtonTrue.setOnClickListener{
            viewFilmAddDelToDataBase?.let { it1 -> it1(act) }
            holder.favouriteButton.isVisible = true
            holder.favouriteButtonTrue.isVisible =false
        }
        holder.favouriteButton.setOnClickListener{
            viewFilmAddDelToDataBase?.let { it1 -> it1(act) }
            holder.favouriteButtonTrue.isVisible = true
            holder.favouriteButton.isVisible =false
        }
    }

    fun itemAmount(){
        listInAdapter = ArrayList()
        notifyDataSetChanged()
    }

}
