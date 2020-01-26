package com.imdb.imdb_api.ui.films

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apka6.DBHelper
import com.imdb.imdb_api.ui.actors.ActorsClass
import com.imdb.imdb_api.ui.directors.DirectorsClass
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmActorsClass
import com.imdb.imdb_api.ui.films.filmDetail.DetailFilmDirectorClass
import com.imdb.imdb_api.ui.films.filmDetail.RestActorsClass
import com.imdb.imdb_api.ui.films.filmDetail.RestDirectorClass
import com.imdb.imdb_api.ui.films.FilmsClass as FilmsClass

class FilmsViewModel:ViewModel() {

    var filmClass = MutableLiveData<FilmsClass>()


    private var restActorsClass = RestActorsClass()
    private var restDirectorClass = RestDirectorClass()
    var batmanMutableActorList= MutableLiveData<DetailFilmActorsClass>()
    var batmanMutableDirectorList= MutableLiveData<DetailFilmDirectorClass>()


    fun sendData(c: FilmsClass) {
        filmClass.value = c
        restActorsClass.searching(filmClass.value?.filmTitle.toString(), filmClass.value?.filmYear.toString())
        batmanMutableActorList=restActorsClass.BatmanMutableList
        restDirectorClass.searching(filmClass.value?.filmTitle.toString(),filmClass.value?.filmYear.toString())
        batmanMutableDirectorList=restDirectorClass.BatmanMutableList
    }



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

    fun checkActorsFromDetail(act : DetailFilmActorsClass, context: Context){
        var db = DBHelper(context,null)
            if(db.checkActor(act.Actors)==1) {
                db.delActor(act.Actors)
            }
            else {
                var helper = ActorsClass(act.Actors)
                db.addActor(helper)
            }

    }

    fun checkDirectorsFromDetail(act:DetailFilmDirectorClass,context: Context){
        var db= DBHelper(context,null)
            if(db.checkDirector(act.Director)==1) {
                db.delDirector(act.Director)
            }
            else {
                var helper = DirectorsClass(act.Director)
                db.addDirector(helper)
            }

    }
    fun checkifActorExistsInDB(act:DetailFilmActorsClass, context: Context): Int {
        val db=DBHelper(context,null)
        return db.checkActor(act.Actors)
    }
    fun checkifDirectorExistsInDB(act:DetailFilmDirectorClass, context: Context): Int {
        val db = DBHelper(context,null)
        return db.checkDirector(act.Director)
    }

    fun editLike(i: Int , context: Context, title:String, year: String){
        val db= DBHelper(context, null)
        db.editLikeFilm(i, title, year)
    }

    fun getFilmClassName(): LiveData<FilmsClass> {
        return filmClass
    }
    fun getFilmLikeId(context: Context, title: String, year: String):String{
        val db= DBHelper(context, null)
        return db.getFilmLikeId( title, year)

    }


}
