package com.example.apka6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.MainActivity
import com.imdb.imdb_api.R
import com.imdb.imdb_api.interfaces.ITest
import com.imdb.imdb_api.ui.actors.ActorsClass
import com.imdb.imdb_api.ui.actors.ActorsFragment
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.FilmsFragment

class AdapterActors(val context: Context, val actorList: MutableList<ActorsClass>, viewActorClassToDataBase: (c:ActorsClass) -> Unit): RecyclerView.Adapter<AdapterActors.ViewHolder>(), ITest {
    private var viewActorClassToDataBase: ((ActorsClass) -> Unit)? = null

    init {
        actorList.sortBy { it.actorID }
        this.viewActorClassToDataBase = viewActorClassToDataBase
    }
//region 1.5
//    init {
//        actorList.sortBy { it.actorName }
//        this.viewActorClassToDataBase = viewActorClassToDataBase
//    }
//endregion

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val act = actorList[position]
        val test = act.actorName
        holder.actorNameTextView.text = test
        holder.favouriteButtonTrue.isVisible = true
        holder.favouriteButton.isVisible = false

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

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val actorNameTextView=view.findViewById<TextView>(R.id.titleMovie)
        val favouriteButton = view.findViewById<ImageButton>(R.id.movieIsFavourite)
        val favouriteButtonTrue = view.findViewById<ImageButton>(R.id.movieIsFavouriteTrue)

    }



}