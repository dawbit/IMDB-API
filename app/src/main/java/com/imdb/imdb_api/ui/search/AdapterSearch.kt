package com.example.apka6

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.scale
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.search.SearchFilmApi
import com.imdb.imdb_api.ui.search.SearchMovieFixed
import kotlinx.android.synthetic.main.item_movies_rv.view.*
import java.net.URL
import java.util.ArrayList

class AdapterSearch : RecyclerView.Adapter<AdapterSearch.ViewHolder>() {

    private var listInAdapter = ArrayList<SearchFilmApi>()

    fun setMovies(list: ArrayList<SearchFilmApi>){
        listInAdapter= list
        notifyDataSetChanged()
    }



    inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val movieTitleTextView = view.titleMovie!!
        val movieYearTextView = view.yearMovie!!
        val moviePosterImageView = view.imageMovie!!
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
        holder.movieTitleTextView.text=act.Title
        holder.movieYearTextView.text=act.Year
        if(act.Poster!="N/A") {
            val url = URL(act.Poster)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            holder.moviePosterImageView.setImageBitmap(bmp.scale(74,74,false))
        }
    }

    fun itemAmount(){
        listInAdapter = ArrayList()
        notifyDataSetChanged()
    }

}
