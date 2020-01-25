package com.example.apka6

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.imdb.imdb_api.ui.actors.ActorsClass
import com.imdb.imdb_api.ui.directors.DirectorsClass
import com.imdb.imdb_api.ui.films.FilmsClass
import com.imdb.imdb_api.ui.films.FilmsFragment

class DBHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATA_BASE_NAME,
        factory, DATA_BASE_VERSION) {

    companion object{
        internal const val DATA_BASE_NAME = "DataBaseFilms.db"
        internal const val DATA_BASE_VERSION = 13
        internal const val TABLE_FILMS = "films"
        internal const val TABLE_ACTORS = "actors"
        internal const val TABLE_DIRECTORS = "directors"
        internal const val COL_ID = "id"
        internal const val COL_NAME = "name"
        internal const val COL_TITLE = "title"
        internal const val COL_YEAR = "year"
        internal const val COL_POSTER = "poster"
        internal const val COL_IMDBID = "imdbID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_films_table = (
                "CREATE TABLE IF NOT EXISTS $TABLE_FILMS ($COL_ID INTEGER PRIMARY KEY, $COL_TITLE TEXT, $COL_YEAR TEXT, $COL_POSTER, $COL_IMDBID)"
                )
        val create_actors_table = (
                "CREATE TABLE IF NOT EXISTS $TABLE_ACTORS ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT)"
                )
        val create_directors_table = (
                "CREATE TABLE IF NOT EXISTS $TABLE_DIRECTORS ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT)"
                )
        Log.d("xdd","SDFgsdg" )
        db!!.execSQL(create_directors_table)
        db.execSQL(create_films_table)
        db.execSQL(create_actors_table)
        Log.d("xdd","SDFgsdg" )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_FILMS")
        onCreate(db)
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_ACTORS")
        onCreate(db)
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_DIRECTORS")
        onCreate(db)
    }
    fun addFilm(film : FilmsClass){
        val values = ContentValues()
        values.put(COL_ID, film.filmID)
        values.put(COL_TITLE, film.filmTitle)
        values.put(COL_YEAR, film.filmYear)
        values.put(COL_POSTER, film.filmPoster)
        values.put(COL_IMDBID, film.imdbID)

        val db = this.writableDatabase
        db.insert(TABLE_FILMS, null, values)
        db.close()
    }

    val allFilms : MutableList<FilmsClass>
        get() {
            val FilmList = mutableListOf<FilmsClass>()
            val db = this.readableDatabase
            val cursor = db!!.rawQuery(" SELECT * FROM $TABLE_FILMS", null)

            if(cursor.moveToFirst()){
                do{
                    val id = cursor.getLong(cursor.getColumnIndex(COL_ID))
                    val title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                    val year = cursor.getString(cursor.getColumnIndex(COL_YEAR))
                    val poster = cursor.getString(cursor.getColumnIndex(COL_POSTER))
                    val imdbID = cursor.getString(cursor.getColumnIndex(COL_IMDBID))

                    //FilmList.add(FilmsClass(title, year, poster, imdbID, id))
                    FilmList.add(FilmsClass(title, year, poster, imdbID, id))
                }while(cursor.moveToNext())
            }
            db.close()

            return FilmList
        }
    fun delFilm(title: String, year: String){
        val db = this.writableDatabase

        db.execSQL("DELETE FROM " + TABLE_FILMS + " WHERE $COL_TITLE= '$title' and $COL_YEAR= '$year'")
        db.close()
    }
    fun addActor(actor : ActorsClass){
        val values = ContentValues()
        values.put(COL_ID, actor.actorID)
        values.put(COL_NAME, actor.actorName)
        val db = this.writableDatabase
        db.insert(TABLE_ACTORS, null, values)
        db.close()
    }

    val allActors : MutableList<ActorsClass>
        get() {
            val ActorList = mutableListOf<ActorsClass>()
            val db = this.readableDatabase
            val cursor = db!!.rawQuery(" SELECT * FROM $TABLE_ACTORS", null)

            if(cursor.moveToFirst()){
                do{
                    val id = cursor.getLong(cursor.getColumnIndex(COL_ID))
                    val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    ActorList.add(ActorsClass(name, id))
                }while(cursor.moveToNext())
            }
            db.close()

            return ActorList
        }
    fun delActor(title:String){
        val db = this.writableDatabase

        db.execSQL("DELETE FROM " + TABLE_ACTORS + " WHERE $COL_NAME= '$title'")
        db.close()
    }
    fun addDirector(director : DirectorsClass){
        val values = ContentValues()
        values.put(COL_ID, director.directorID)
        values.put(COL_NAME, director.directorName)
        val db = this.writableDatabase
        db.insert(TABLE_DIRECTORS, null, values)
        db.close()
    }

    val allDirectors : MutableList<DirectorsClass>
        get() {
            val DirectorList = mutableListOf<DirectorsClass>()
            val db = this.readableDatabase
            val cursor = db!!.rawQuery(" SELECT * FROM $TABLE_DIRECTORS", null)

            if(cursor.moveToFirst()){
                do{
                    val id = cursor.getLong(cursor.getColumnIndex(COL_ID))
                    val name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    DirectorList.add(DirectorsClass(name, id))
                }while(cursor.moveToNext())
            }
            db.close()

            return DirectorList
        }
    fun delDirector(id:String){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_DIRECTORS + " WHERE $COL_NAME='" + id + "'")
        db.close()
    }

    fun checkFilm (title: String, year: String) :Int  {
        val i :Int
        val db = this.readableDatabase
        val cursor = db!!.rawQuery("SELECT * FROM $TABLE_FILMS WHERE $COL_TITLE= '$title' and $COL_YEAR= '$year'", null)
        Log.d("cycki" ,cursor.count.toString())
        i=cursor.count.toString().toInt()
        db.close()
        return i
    }
    fun checkActor (title: String) :Int  {
        val i :Int
        val db = this.readableDatabase
        val cursor = db!!.rawQuery("SELECT * FROM $TABLE_ACTORS WHERE $COL_NAME= '$title'", null)
        Log.d("cycki" ,cursor.count.toString())
        i=cursor.count.toString().toInt()
        db.close()
        return i
    }
    fun checkDirector (title: String) :Int  {
        val i :Int
        val db = this.readableDatabase
        val cursor = db!!.rawQuery("SELECT * FROM $TABLE_DIRECTORS WHERE $COL_NAME= '$title'", null)
        Log.d("cycki" ,cursor.count.toString())
        i=cursor.count.toString().toInt()
        db.close()
        return i
    }

}