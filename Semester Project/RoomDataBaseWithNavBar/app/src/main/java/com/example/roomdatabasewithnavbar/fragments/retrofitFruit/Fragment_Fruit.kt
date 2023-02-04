package com.example.roomdatabasewithnavbar.fragments.retrofitFruit

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
import com.example.roomdatabasewithnavbar.repository.networkRepository.APIService
import com.example.roomdatabasewithnavbar.repository.networkRepository.RetroInstance
import kotlinx.android.synthetic.main.recyclerview_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentFruit : Fragment(),ClickListener{


    var fruitList = ArrayList<FruitDataModel>()
   // lateinit var adapter:FruitAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment__favourites, container, false)


        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = FruitAdapter(this@FragmentFruit.requireContext(), this@FragmentFruit,true,true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@FragmentFruit.requireContext())

        getFruitList(adapter)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragmentFruit()
    }

    override fun onRowClick(position: Int) {
    }

    override fun onViewClick(position: Int, view: View) {
        TODO("Not yet implemented")
    }



    // /Todo: Api Calls
    fun getFruitList (adapter:FruitAdapter) {
        val retrofitInstance = RetroInstance.getRetrofitInstance()
            .create(APIService::class.java)
        val cal = retrofitInstance.getFruitsListFromModel()
        cal.enqueue(object : Callback<FruitModel> {
            override fun onResponse(
                call: Call<FruitModel>,
                response: Response<FruitModel>
            ) {

                if (response.isSuccessful) {
                    if (response.body()!=null) {
                        fruitList = response.body()?.products!!
                        var  list=ArrayList<Fruit>()
                        for (item in fruitList){
                            list.add(Fruit(item.name,"",false))
                        }
                        adapter.setDataList(list)
                    }
                }
            }

            override fun onFailure(call: Call<FruitModel>, t: Throwable) {
            }
        })
    }

}