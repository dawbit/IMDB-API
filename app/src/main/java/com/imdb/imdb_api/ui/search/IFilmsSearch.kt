package com.imdb.imdb_api.ui.search

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface IFilmsSearch {
//
//    @GET ("?apikey=a7a8d1d6&s=batman")
//    fun getBatman(): retrofit2.Call<SearchMovieFixed>
    @GET ("?apikey=a7a8d1d6")
    fun newSearch(@Query("s") zmienna : String): retrofit2.Call<SearchMovieFixed>
}