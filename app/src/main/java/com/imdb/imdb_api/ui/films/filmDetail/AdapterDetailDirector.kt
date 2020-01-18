package com.example.apka6

import android.content.Context
import android.util.Log
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
import com.imdb.imdb_api.ui.actors.ActorsClass
import com.imdb.imdb_api.ui.directors.DirectorsClass
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmActorsClass
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmDirectorClass

class AdapterDetailDirector(context: Context) : RecyclerView.Adapter<AdapterDetailDirector.ViewHolder>() {

    private var context= context
    private var listInAdapterDirector = ArrayList<DetailFilmDirectorClass>()

    fun setData(list: String){
        Log.d("czekie", list)
        var result = list.split(",").map { it.trim() }

        result.forEach {
            var asdList = DetailFilmDirectorClass(it.toString())
            listInAdapterDirector.add(asdList)
        }

        notifyDataSetChanged()
    }



    inner class  ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val NameTextView = view.titleMovie!!
        val favouriteButton = view.movieIsFavourite
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

        var db = DBHelper(context,null)
        if(db.checkDirector(act.Director)==1){
            holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
        }
        holder.NameTextView.text=act.Director


    holder.favouriteButton.setOnClickListener{
        if(db.checkDirector(act.Director)==1) {
            db.delDirector(act.Director)
            holder.favouriteButton.setImageResource(R.drawable.ic_favourite_false)
        }
        else {
            var helper = DirectorsClass(act.Director)
            db.addDirector(helper)
            holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
        }
    }

    }



}
