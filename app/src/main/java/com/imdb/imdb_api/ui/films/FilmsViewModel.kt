package com.imdb.imdb_api.ui.films

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apka6.DBHelper

class FilmsViewModel:ViewModel() {

    private var filmClass = MutableLiveData<FilmsClass>()

    fun getClassToMakeChangeInDB(a: FilmsClass, context: Context) {
        val dbHandler = DBHelper(context, null)

        if(dbHandler.checkFilm(a.filmTitle, a.filmYear)==1) {
            dbHandler.delFilm(a.filmTitle, a.filmYear)
        }
        else {
            dbHandler.addFilm(a)
        }
    }
    fun getAllFilmsFromDB(context: Context) : MutableList<FilmsClass>{
        val dbHandler = DBHelper(context, null)
        return dbHandler.allFilms
    }
}
