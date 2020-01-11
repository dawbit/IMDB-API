package com.imdb.imdb_api.ui.directors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DirectorsViewModel:ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is direc Fragment"
    }
    val text: LiveData<String> = _text
}