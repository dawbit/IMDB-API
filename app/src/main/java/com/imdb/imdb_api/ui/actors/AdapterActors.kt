package com.example.apka6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val test = act.actorForeName + act.actorLastName
        holder.actorNameTextView.text = test

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
        val actorNameTextView=view.findViewById<TextView>(R.id.titleMovie)
    }



}