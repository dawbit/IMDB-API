package com.imdb.imdb_api.ui.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilmsViewModel:ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is films Fragment"
    }
    val text: LiveData<String> = _text
}
