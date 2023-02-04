package com.example.roomdatabasewithnavbar.fragments.favourite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasewithnavbar.ClickListener
import com.example.roomdatabasewithnavbar.R
import com.example.roomdatabasewithnavbar.fragments.list.adapter.FruitAdapter
import com.example.roomdatabasewithnavbar.repository.Fruit
import com.example.roomdatabasewithnavbar.repository.WordViewModel
import com.example.roomdatabasewithnavbar.repository.WordViewModelFactory
import com.example.roomdatabasewithnavbar.repository.WordsApplication
import kotlinx.android.synthetic.main.recyclerview_items.*


class FragmentFavourites : Fragment(),ClickListener{
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((this@FragmentFavourites.activity?.application as WordsApplication).repository)
    }

    var fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment__favourites, container, false)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = FruitAdapter(this@FragmentFavourites.requireContext(), this@FragmentFavourites,true,false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@FragmentFavourites.requireContext())


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(viewLifecycleOwner) { words ->
            // Update the cached copy of the words in the adapter.
            words.let {

                //Todo: just populate favourite Items
                for (item in it){
                    if (item.Favourite){
                        fruitList.add(item)
                    }
                }

                adapter.setDataList(fruitList)
            }
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragmentFavourites()
    }

    override fun onRowClick(position: Int) {
    }

    override fun onViewClick(position: Int, view: View) {
        TODO("Not yet implemented")
    }
}