package com.imdb.imdb_api.ui.films.filmDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.typeOf

class RestActorsClass : Callback<DetailFilmActorsClass> {
    var BatmanMutableList:MutableLiveData<DetailFilmActorsClass> = MutableLiveData()


    fun searching(s: String, y: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val irest = retrofit.create(IActorsDirectorSearch::class.java)
        val kolejnazmienna = irest.newActorSearch(s,y)
        kolejnazmienna.enqueue(this)
    }

    override fun onFailure(call: Call<DetailFilmActorsClass>?, t: Throwable?) {
        t?.printStackTrace()
    }

    override fun onResponse(call: Call<DetailFilmActorsClass>?, response: Response<DetailFilmActorsClass>?) {
        if (response != null) {
             if(response.isSuccessful) {
                 BatmanMutableList.value = response.body()
               //  Log.d("majestat", response.body().Actors)
             }

        }
    }
}