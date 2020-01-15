package com.example.apka6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.imdb.imdb_api.R
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.FilmsFragment

class AdapterFilms(val context: Context, val filmlist: MutableList<FilmsClass>): RecyclerView.Adapter<AdapterFilms.ViewHolder>() {
    init {
        filmlist.sortBy { it.filmID }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_rv, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var dbHandler = DBHelper(context, null)
        val fil = filmlist[position]
        holder.filmTitleTextView.text = fil.filmTitle
        holder.filmYearTextView.text = fil.filmYear

        // dbHandler.delUser(act)
        // tasks.removeAt(position)
        // notifyItemRemoved(position)
        // notifyItemRangeChanged(position, this.itemCount)

//        holder.itemView.setOnClickListener()
//
//        {
//            val intent = Intent(context,SecondActivity::class.java)
//            intent.putExtra("name", act.taskName)
//            intent.putExtra("priority", act.taskPriority)
//            intent.putExtra("state", act.taskState)
//            intent.putExtra("id",act.taskid.toString())
//            ContextCompat.startActivity(context,intent,null)
//            Log.d("kiciak",act.taskid.toString())
//        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val filmTitleTextView=view.findViewById<TextView>(R.id.titleMovie)
        val filmYearTextView=view.findViewById<TextView>(R.id.yearMovie)
    }


}