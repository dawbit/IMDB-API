package com.imdb.imdb_api.ui.search

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apka6.DBHelper
import com.imdb.imdb_api.ui.films.FilmsClass

class SearchViewModel : ViewModel() {
    var batmanMutableList: MutableLiveData<SearchMovieFixed> = MutableLiveData()

    private val restFilmClass = RestFilmClass()


    fun setSearchStringMovie(s:String){
        restFilmClass.searching(s)

    }
    fun restClassStart() {
        this.batmanMutableList= restFilmClass.BatmanMutableList
    }

    fun checkIfFilmIsFavourite(context: Context, act: SearchFilmApi) : Int{
        var t = act.Title.replace("'","")
        t = t.replace("\"", "")
        val db = DBHelper(context, null)
        return if(db.checkFilm(t, act.Year)==1){
            1
        } else{
            0
        }
    }
    fun viewFilmAddDelToDataBase(context: Context, act:SearchFilmApi){
        val dbHandler = DBHelper(context, null)

        if (dbHandler.checkFilm(act.Title, act.Year) == 1) {
            dbHandler.delFilm(act.Title, act.Year)
        } else {
            val helper = FilmsClass(act.Title, act.Year,act.Poster)
            dbHandler.addFilm(helper)
        }
    }
}