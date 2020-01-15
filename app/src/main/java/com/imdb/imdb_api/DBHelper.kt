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
        internal const val DATA_BASE_VERSION = 2
        internal const val TABLE_FILMS = "films"
        internal const val TABLE_ACTORS = "actors"
        internal const val TABLE_DIRECTORS = "directors"
        internal const val COL_ID = "id"
        internal const val COL_FORENAME = "forename"
        internal const val COL_LASTNAME = "lastname"
        internal const val COL_TITLE = "title"
        internal const val COL_YEAR = "year"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_films_table = (
                "CREATE TABLE IF NOT EXISTS $TABLE_FILMS ($COL_ID INTEGER PRIMARY KEY, $COL_TITLE TEXT, $COL_YEAR TEXT)"
                )
        val create_actors_table = (
                "CREATE TABLE IF NOT EXISTS $TABLE_ACTORS ($COL_ID INTEGER PRIMARY KEY, $COL_FORENAME TEXT, $COL_LASTNAME TEXT)"
                )
        val create_directors_table = (
                "CREATE TABLE IF NOT EXISTS $TABLE_DIRECTORS ($COL_ID INTEGER PRIMARY KEY, $COL_FORENAME TEXT, $COL_LASTNAME TEXT)"
                )
        Log.d("xdd","SDFgsdg" )
        db!!.execSQL(create_directors_table)
        db!!.execSQL(create_films_table)
        db!!.execSQL(create_actors_table)
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
                    FilmList.add(FilmsClass(title, year, id))
                }while(cursor.moveToNext())
            }
            db.close()

            return FilmList
        }
    fun delFilm(id:String){
        val db = this.writableDatabase

        db.execSQL("DELETE FROM " + TABLE_FILMS + " WHERE $COL_ID='" + id + "'")
        db.close()
    }
    fun addActor(actor : ActorsClass){
        val values = ContentValues()
        values.put(COL_ID, actor.actorID)
        values.put(COL_FORENAME, actor.actorForeName)
        values.put(COL_LASTNAME, actor.actorLastName)
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
                    val forename = cursor.getString(cursor.getColumnIndex(COL_FORENAME))
                    val lastname = cursor.getString(cursor.getColumnIndex(COL_LASTNAME))
                    ActorList.add(ActorsClass(forename, lastname, id))
                }while(cursor.moveToNext())
            }
            db.close()

            return ActorList
        }
    fun delActor(id:String){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_ACTORS + " WHERE $COL_ID='" + id + "'")
        db.close()
    }
    fun addDirector(director : DirectorsClass){
        val values = ContentValues()
        values.put(COL_ID, director.directorID)
        values.put(COL_FORENAME, director.directorForeName)
        values.put(COL_LASTNAME, director.directorLastName)
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
                    val forename = cursor.getString(cursor.getColumnIndex(COL_FORENAME))
                    val lastname = cursor.getString(cursor.getColumnIndex(COL_LASTNAME))
                    DirectorList.add(DirectorsClass(forename, lastname, id))
                }while(cursor.moveToNext())
            }
            db.close()

            return DirectorList
        }
    fun delDirector(id:String){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_DIRECTORS + " WHERE $COL_ID='" + id + "'")
        db.close()
    }

}