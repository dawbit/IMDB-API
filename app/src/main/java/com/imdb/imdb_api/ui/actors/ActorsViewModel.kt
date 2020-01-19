package com.imdb.imdb_api.ui.actors

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apka6.DBHelper

class ActorsViewModel: ViewModel() {


    fun getClassToMakeChangeInDB(a: ActorsClass, context: Context) {
        val dbHandler = DBHelper(context, null)

        if (dbHandler.checkActor(a.actorName) == 1) {
            dbHandler.delActor(a.actorName)
        } else {
            dbHandler.addActor(a)
        }
    }

    fun getAllActorsFromDB(context: Context) : MutableList<ActorsClass>{
        val dbHandler = DBHelper(context, null)
        return dbHandler.allActors
    }
}

