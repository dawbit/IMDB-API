package com.imdb.imdb_api.ui.directors

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apka6.DBHelper

class DirectorsViewModel:ViewModel() {

    fun getClassToMakeChangeInDB(a: DirectorsClass, context: Context) {
        val dbHandler = DBHelper(context, null)

        if (dbHandler.checkDirector(a.directorName) == 1) {
            dbHandler.delDirector(a.directorName)
        } else {
            dbHandler.addDirector(a)
        }
    }

    fun getAllActorsFromDB(context: Context) : MutableList<DirectorsClass>{
        val dbHandler = DBHelper(context, null)
        return dbHandler.allDirectors
    }
}