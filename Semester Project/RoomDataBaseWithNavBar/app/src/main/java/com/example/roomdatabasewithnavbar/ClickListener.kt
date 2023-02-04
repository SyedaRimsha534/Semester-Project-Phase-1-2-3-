package com.example.roomdatabasewithnavbar

import android.view.View

interface ClickListener {
    fun onRowClick(position:Int)
    fun onViewClick(position: Int,view: View)
}