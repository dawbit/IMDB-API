package com.imdb.imdb_api.ui.films.filmDetail

import com.imdb.imdb_api.ui.search.SearchMovieFixed
import retrofit2.http.GET
import retrofit2.http.Query

interface IActorsDirectorSearch {
    @GET("?apikey=a7a8d1d6")
    fun newActorSearch(@Query("t") zmienna : String): retrofit2.Call<DetailFilmActorsClass>

    @GET ("?apikey=a7a8d1d6")
    fun newDirectorSearch(@Query("t") zmienna : String): retrofit2.Call<DetailFilmDirectorClass>
}