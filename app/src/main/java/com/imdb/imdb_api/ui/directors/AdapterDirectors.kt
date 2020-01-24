package com.example.apka6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.directors.DirectorsClass

class AdapterDirectors(val context: Context, val directorsList: MutableList<DirectorsClass>, viewDirectorClassToDataBase: (c:DirectorsClass) -> Unit): RecyclerView.Adapter<AdapterDirectors.ViewHolder>() {

    private var viewDirectorClassToDataBase: ((DirectorsClass) -> Unit)? = null
    init {
        directorsList.sortBy { it.directorID }
        this.viewDirectorClassToDataBase = viewDirectorClassToDataBase
    }

//region 1.5
//    init {
//        directorsList.sortBy { it.directorName }
//        this.viewDirectorClassToDataBase = viewDirectorClassToDataBase
//    }
//endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return directorsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val act = directorsList[position]
        val test = act.directorName
        holder.directorNameTextView.text = test

        holder.favouriteButtonTrue.isVisible = true
        holder.favouriteButton.isVisible = false

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

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val directorNameTextView=view.findViewById<TextView>(R.id.titleMovie)
        val favouriteButton = view.findViewById<ImageButton>(R.id.movieIsFavourite)
        val favouriteButtonTrue = view.findViewById<ImageButton>(R.id.movieIsFavouriteTrue)
    }


}