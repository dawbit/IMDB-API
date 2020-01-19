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
import com.imdb.imdb_api.ui.actors.ActorsClass
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmActorsClass

class AdapterDetail(context: Context, viewActorClassToDataBase: (c:DetailFilmActorsClass) -> Unit, checkifActorExistsInDB: (c:DetailFilmActorsClass) -> Int) : RecyclerView.Adapter<AdapterDetail.ViewHolder>() {
    private var context= context
    private var listInAdapterActors = ArrayList<DetailFilmActorsClass>()
    private var viewActorClassToDataBase: ((c:DetailFilmActorsClass) -> Unit)?=null
    private var checkifActorExistsInDB: ((c:DetailFilmActorsClass) -> Int)?=null
    init {
        this.viewActorClassToDataBase=viewActorClassToDataBase
        this.checkifActorExistsInDB=checkifActorExistsInDB
    }
    fun setData(list: String){
        listInAdapterActors.clear()
        Log.d("czekie", list)
        var result = list.split(",").map { it.trim() }

        result.forEach {
            var asdList = DetailFilmActorsClass(it.toString())
            listInAdapterActors.add(asdList)
        }

        notifyDataSetChanged()
    }



    inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val NameTextView = view.titleMovie!!
        val favouriteButton = view.movieIsFavourite
        val favouriteButtonTrue= view.movieIsFavouriteTrue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listInAdapterActors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val act = listInAdapterActors[position]
        holder.NameTextView.text=act.Actors


        if(checkifActorExistsInDB?.let { it(act) } ==1){
            holder.favouriteButton.isVisible = false
            holder.favouriteButtonTrue.isVisible =true
        }
        else{
            holder.favouriteButton.isVisible = true
            holder.favouriteButtonTrue.isVisible =false
        }




        holder.favouriteButtonTrue.setOnClickListener{
            viewActorClassToDataBase?.let { it1 -> it1(act) }
            holder.favouriteButton.isVisible = true
            holder.favouriteButtonTrue.isVisible =false
        }
        holder.favouriteButton.setOnClickListener{
            viewActorClassToDataBase?.let { it1 -> it1(act) }
            holder.favouriteButtonTrue.isVisible = true
            holder.favouriteButton.isVisible =false
        }

    }



}
