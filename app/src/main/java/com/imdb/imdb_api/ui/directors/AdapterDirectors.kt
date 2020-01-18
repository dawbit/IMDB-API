package com.example.apka6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.directors.DirectorsClass

class AdapterDirectors(val context: Context, val directorsList: MutableList<DirectorsClass>): RecyclerView.Adapter<AdapterDirectors.ViewHolder>() {
    init {
        directorsList.sortBy { it.directorID }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return directorsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dbHandler = DBHelper(context, null)
        val act = directorsList[position]
        val test = act.directorName
        holder.directorNameTextView.text = test

        holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
        holder.favouriteButton.setOnClickListener{

            if(dbHandler.checkDirector(test)==1) {
                dbHandler.delDirector(act.directorName)
                holder.favouriteButton.setImageResource(R.drawable.ic_favourite_false)
            }
            else {
                var helper = DirectorsClass(act.directorName)
                dbHandler.addDirector(helper)
                holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
            }
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val directorNameTextView=view.findViewById<TextView>(R.id.titleMovie)
        val favouriteButton = view.findViewById<ImageButton>(R.id.movieIsFavourite)
    }


}