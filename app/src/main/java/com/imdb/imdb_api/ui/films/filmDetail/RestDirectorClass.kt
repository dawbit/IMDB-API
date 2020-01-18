package com.imdb.imdb_api.ui.films.filmDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.typeOf

class RestDirectorClass : Callback<DetailFilmDirectorClass> {
    var BatmanMutableList: MutableLiveData<DetailFilmDirectorClass> = MutableLiveData()
    fun searching(s: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val irest = retrofit.create(IActorsDirectorSearch::class.java)
        val kolejnazmienna = irest.newDirectorSearch(s)
        kolejnazmienna.enqueue(this)
    }

    override fun onFailure(call: Call<DetailFilmDirectorClass>?, t: Throwable?) {
        t?.printStackTrace()
    }

    override fun onResponse(call: Call<DetailFilmDirectorClass>?, response: Response<DetailFilmDirectorClass>?) {
        if (response != null) {
             if(response.isSuccessful) {
                 BatmanMutableList.value = response.body()
             }

        }
    }
}