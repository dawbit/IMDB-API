package com.imdb.imdb_api.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.typeOf

class RestFilmClass : Callback<SearchMovieFixed> {
    var BatmanMutableList: MutableLiveData<SearchMovieFixed> = MutableLiveData()
   // http://www.omdbapi.com/?s=Batman&apikey=a7a8d1d6
    fun searching(s: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val irest = retrofit.create(IFilmsSearch::class.java)
        val kolejnazmienna = irest.newSearch(s)
        kolejnazmienna.enqueue(this)
    }

    override fun onFailure(call: Call<SearchMovieFixed>?, t: Throwable?) {
        t?.printStackTrace()
    }

    override fun onResponse(call: Call<SearchMovieFixed>?, response: Response<SearchMovieFixed>?) {
        if (response != null) {
            if (response.body().Response=="True") {
                BatmanMutableList.value = response.body()
            }
            else{
                BatmanMutableList.value = null
            }
        }
    }
}