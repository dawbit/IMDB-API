package com.imdb.imdb_api.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val text = MutableLiveData<String>()

    fun setSearchStringMovie(s:String){
        text.value=s
    }



    fun getString():LiveData<String>{
        return text
    }
}
