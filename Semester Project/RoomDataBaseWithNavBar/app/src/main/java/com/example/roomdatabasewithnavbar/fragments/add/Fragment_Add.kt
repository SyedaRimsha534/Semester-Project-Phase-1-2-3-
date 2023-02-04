package com.example.roomdatabasewithnavbar.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.roomdatabasewithnavbar.R
import com.example.roomdatabasewithnavbar.repository.Fruit
import com.example.roomdatabasewithnavbar.repository.WordViewModel
import com.example.roomdatabasewithnavbar.repository.WordViewModelFactory
import com.example.roomdatabasewithnavbar.repository.WordsApplication


class FragmentAdd : Fragment() {

    private val fruitViewModel: WordViewModel by viewModels {
        WordViewModelFactory((this@FragmentAdd.activity?.application as WordsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view=inflater.inflate(R.layout.fragment__add, container, false)

        val fruitName = view.findViewById<EditText>(R.id.fruitName)
        val fruitDescription = view.findViewById<EditText>(R.id.fruitDescription)
        val button = view.findViewById<Button>(R.id.addButton)

        button.setOnClickListener {
            val fruitNameText = fruitName.text.toString()
            val fruitDescriptionText = fruitDescription.text.toString()

            if (fruitNameText.isNotEmpty() && fruitDescriptionText.isNotEmpty()){
                fruitViewModel.insert(Fruit(fruitNameText,fruitDescriptionText,false))
                Toast.makeText(this@FragmentAdd.requireContext(),"You have successfully add the fruit item.",Toast.LENGTH_SHORT).show()

                fruitName.text.clear()
                fruitDescription.text.clear()

            }else{
                Toast.makeText(this@FragmentAdd.requireContext(),"Please fill the form",Toast.LENGTH_SHORT).show()
            }
        }

        return view;
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragmentAdd()

    }
}