package com.example.todoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.todoapp.R
import com.example.todoapp.models.Spinner

class MySpinerAdapter(private var list: ArrayList<Spinner>): BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemView: View
        if (convertView==null){
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_spinner,parent,false)
        }else{
            itemView = convertView
        }
        itemView.findViewById<TextView>(R.id.tv_item).text = list[position].name
        if (list[position].image!=-1)
        itemView.findViewById<ImageView>(R.id.img).setImageResource(list[position].image)
        return itemView
    }
}