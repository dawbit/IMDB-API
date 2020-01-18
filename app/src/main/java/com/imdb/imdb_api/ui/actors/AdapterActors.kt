package com.example.apka6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.actors.ActorsClass
import com.imdb.imdb_api.ui.actors.ActorsFragment
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.FilmsFragment

class AdapterActors(val context: Context, val actorList: MutableList<ActorsClass>): RecyclerView.Adapter<AdapterActors.ViewHolder>() {
    init {
        actorList.sortBy { it.actorID }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dbHandler = DBHelper(context, null)
        val act = actorList[position]
        val test = act.actorName
        //holder.favouriteButton
        holder.actorNameTextView.text = test
        holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
        holder.favouriteButton.setOnClickListener{

            if(dbHandler.checkActor(test)==1) {
                dbHandler.delActor(act.actorName)
                holder.favouriteButton.setImageResource(R.drawable.ic_favourite_false)
            }
            else {
                var helper = ActorsClass(act.actorName)
                dbHandler.addActor(helper)
                holder.favouriteButton.setImageResource(R.drawable.ic_favourite_true)
            }
        }

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val actorNameTextView=view.findViewById<TextView>(R.id.titleMovie)
        val favouriteButton = view.findViewById<ImageButton>(R.id.movieIsFavourite)
    }



}