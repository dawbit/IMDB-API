package com.imdb.imdb_api.ui.search

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apka6.AdapterSearch

import com.imdb.imdb_api.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProviders.of(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel = activity!!.run{ViewModelProviders.of(this).get(searchViewModel::class.java)}
    //    var rest= RestFilmClass()

    //    rest.BatmanMutableList.observe(this, Observer { tester.text = it.Search.first().Title})
        val restFilmClass = RestFilmClass()
        val recyclerView = view!!.findViewById<RecyclerView>(R.id.recyclerViewSearch)
        val adapter = AdapterSearch(requireContext())
        restFilmClass.BatmanMutableList.observe(this, Observer { if (it!=null)adapter.setMovies(ArrayList(it.Search))
        else adapter.itemAmount()
        })
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)
        searchText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchViewModel.setSearchStringMovie(s.toString())
                if(s.toString().length>2) {
                    Log.d("pici", s.toString().trim())
                    restFilmClass.searching(s.toString().trim())
                }
            }


        })
    }
}
