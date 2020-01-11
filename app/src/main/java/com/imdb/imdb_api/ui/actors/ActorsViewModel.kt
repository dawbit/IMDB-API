package com.imdb.imdb_api.ui.actors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActorsViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is actor Fragment"
    }
    val text: LiveData<String> = _text
}