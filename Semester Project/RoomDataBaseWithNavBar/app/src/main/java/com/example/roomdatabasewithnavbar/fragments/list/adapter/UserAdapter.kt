package com.example.roomdatabasewithnavbar.fragments.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasewithnavbar.ClickListener
import com.example.roomdatabasewithnavbar.R
import com.example.roomdatabasewithnavbar.repository.Fruit
import kotlinx.android.synthetic.main.recyclerview_items.view.*


class FruitAdapter(val context: Context, private val listener: ClickListener,val isFavourite:Boolean,val isRetrofit:Boolean):
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    var list = ArrayList<Fruit>()

    fun setDataList(data: ArrayList<Fruit>) {
        this.list = data
        notifyDataSetChanged()
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_items, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]

        holder.title.text = item.FoodName
        holder.description.text = item.FoodDescription

        if (isRetrofit){
            holder.favourite.visibility=View.GONE
        }else{
            holder.favourite.visibility=View.VISIBLE
        }

        if (item.Favourite){
            holder.favourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24))
        }else{
            holder.favourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
        }


        if (!isRetrofit && !isFavourite){
            holder.delete.visibility=View.VISIBLE
        }else{
            holder.delete.visibility=View.GONE
        }

        holder.favourite.setOnClickListener(View.OnClickListener {

            if (!isFavourite) {
                listener.onRowClick(position)

                if (item.Favourite) {
                    holder.favourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
                } else {
                    holder.favourite.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_favorite_24))
                }
            }
        })


        holder.delete.setOnClickListener(View.OnClickListener {
            listener.onViewClick(position,it)
        })


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return list.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val favourite: ImageView = itemView.findViewById(R.id.favourite)
        val delete:ImageView=itemView.findViewById(R.id.delete)



    }
}

/*
class UserAdapter(listener:ClickListener) : ListAdapter<Fruit, UserAdapter.WordViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordItemView: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)
        private val favourite: ImageView = itemView.findViewById(R.id.favourite)

        fun bind(word:Fruit) {
            wordItemView.text = word.FoodName
            description.text=word.FoodDescription
            favourite.setOnClickListener(View.OnClickListener {
                //list
            })
        }


        companion object {
            fun create(parent: ViewGroup): WordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_items, parent, false)
                return WordViewHolder(view)
            }
        }
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Fruit>() {
            override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
                return oldItem.FoodName == newItem.FoodName
            }
        }
    }
}
*/

