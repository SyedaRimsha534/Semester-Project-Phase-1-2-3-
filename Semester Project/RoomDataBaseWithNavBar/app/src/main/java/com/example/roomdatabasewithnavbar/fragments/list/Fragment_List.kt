package com.example.roomdatabasewithnavbar.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
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


class FragmentList : Fragment(), ClickListener {


    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((this@FragmentList.activity?.application as WordsApplication).repository)
    }

    var fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment__list, container, false)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter =
            FruitAdapter(this@FragmentList.requireContext(), this@FragmentList, false, false)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@FragmentList.requireContext())


        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        wordViewModel.allWords.observe(viewLifecycleOwner) { words ->
            // Update the cached copy of the words in the adapter.
            words.let {
                fruitList =
                    it as ArrayList<Fruit> /* = java.util.ArrayList<com.example.roomdatabasewithnavbar.repository.Fruit> */
                adapter.setDataList(it)

                for (item in it) {
                    Log.d("FavouriteIssue", "Favourite :" + item.FoodName + "  " + item.Favourite)
                }
            }
        }

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragmentList()
    }

    override fun onRowClick(position: Int) {
        Log.d("FavouriteIssue", "Size :" + fruitList.size)
        if (fruitList.size >= position) {
            val item = fruitList[position]
            if (item.Favourite) {
                Log.d("FavouriteIssue", "True Case")
                var fruit = Fruit(item.FoodName, item.FoodDescription, false);
//                wordViewModel.update(fruit)
//                wordViewModel.updateWithQuery(fruit)

                wordViewModel.deleteFruit(item)
                wordViewModel.insert(fruit)

            } else {
                Log.d("FavouriteIssue", "False Case")
                var fruit = Fruit(item.FoodName, item.FoodDescription, true);
//                wordViewModel.updateWithQuery(fruit)
//               wordViewModel.update(fruit)


                wordViewModel.deleteFruit(item)
                wordViewModel.insert(fruit)

            }

        }
    }

    override fun onViewClick(position: Int, view: View) {
        Log.d("FavouriteIssue", "Size :" + fruitList.size)
        if (fruitList.size >= position) {
            val item = fruitList[position]
            wordViewModel.deleteFruit(item)
        }
    }
}