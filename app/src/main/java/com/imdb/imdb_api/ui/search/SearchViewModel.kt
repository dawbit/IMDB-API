package com.imdb.imdb_api.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    val text = MutableLiveData<String>()

    fun setString(s:String){
        text.value=s
    }
}
