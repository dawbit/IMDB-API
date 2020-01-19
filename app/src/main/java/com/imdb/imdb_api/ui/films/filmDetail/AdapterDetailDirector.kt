package com.example.apka6

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.search.SearchFilmApi
import kotlinx.android.synthetic.main.item_movies_rv.view.*
import java.net.URL
import java.util.ArrayList
import com.bumptech.glide.Glide
import com.imdb.imdb_api.ui.actors.ActorsClass
import com.imdb.imdb_api.ui.directors.DirectorsClass
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmActorsClass
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmDirectorClass

class AdapterDetailDirector(context: Context, viewDirectorClassToDataBase: (c:DetailFilmDirectorClass) -> Unit, checkifDirectorExistsInDB: (c:DetailFilmDirectorClass) -> Int) : RecyclerView.Adapter<AdapterDetailDirector.ViewHolder>() {

    private var context= context
    private var listInAdapterDirector = ArrayList<DetailFilmDirectorClass>()
    private var viewDirectorClassToDataBase: ((c:DetailFilmDirectorClass) -> Unit)?=null
    private var checkifDirectorExistsInDB: ((c:DetailFilmDirectorClass) -> Int)?=null
init {
    this.viewDirectorClassToDataBase=viewDirectorClassToDataBase
    this.checkifDirectorExistsInDB=checkifDirectorExistsInDB
}
    fun setData(list: String){
        var result = list.split(",").map { it.trim() }

        result.forEach {
            var asdList = DetailFilmDirectorClass(it.toString())
            listInAdapterDirector.add(asdList)
        }
        notifyDataSetChanged()
    }



    inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val NameTextView = view.titleMovie!!
        val favouriteButton: AppCompatImageButton = view.movieIsFavourite
        val favouriteButtonTrue: AppCompatImageButton = view.movieIsFavouriteTrue
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listInAdapterDirector.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val act = listInAdapterDirector[position]
        holder.NameTextView.text=act.Director

        if(checkifDirectorExistsInDB?.let { it(act) } ==1){
            holder.favouriteButton.isVisible = true
            holder.favouriteButtonTrue.isVisible =false
        }
        else{
            holder.favouriteButton.isVisible = true
            holder.favouriteButtonTrue.isVisible =false
        }


        holder.favouriteButtonTrue.setOnClickListener{
            viewDirectorClassToDataBase?.let { it1 -> it1(act) }
            holder.favouriteButton.isVisible = true
            holder.favouriteButtonTrue.isVisible =false
        }
        holder.favouriteButton.setOnClickListener{
            viewDirectorClassToDataBase?.let { it1 -> it1(act) }
            holder.favouriteButtonTrue.isVisible = true
            holder.favouriteButton.isVisible =false
        }

    }



}
