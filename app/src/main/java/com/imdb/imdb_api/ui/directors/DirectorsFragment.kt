package com.imdb.imdb_api.ui.directors


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.imdb.imdb_api.R

/**
 * A simple [Fragment] subclass.
 */
class DirectorsFragment : Fragment() {
    private lateinit var directorsViewModel: DirectorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        directorsViewModel =
            ViewModelProviders.of(this).get(DirectorsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_database, container, false)
        val textView: TextView = root.findViewById(R.id.textView_database)
        directorsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

}
