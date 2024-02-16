package com.example.cgr.panier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.cgr.R

class PanierAdapter(private val personnes: Array<String>) : BaseAdapter() {

    override fun getCount(): Int {
        return personnes.size
    }

    override fun getItem(position: Int): String {
        return personnes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent!!.context).inflate(R.layout.item_panier, parent, false)
        val data = getItem(position)
        val nomTextView = view.findViewById<TextView>(R.id.name)
        nomTextView.text = data
        return view
    }
}