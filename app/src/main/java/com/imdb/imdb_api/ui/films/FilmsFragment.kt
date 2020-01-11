package com.imdb.imdb_api.ui.films


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.imdb.imdb_api.R

/**
 * A simple [Fragment] subclass.
 */
class FilmsFragment : Fragment() {

    private lateinit var filmsViewModel: FilmsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        filmsViewModel =
            ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_database, container, false)
        val textView: TextView = root.findViewById(R.id.textView_database)
        filmsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }


}
