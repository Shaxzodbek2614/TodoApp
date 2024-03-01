package com.example.todoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.todoapp.R

class ExpandableAdapter(var titleList:ArrayList<String>, var map: Map<String,ArrayList<String>>):BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return titleList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return map[titleList[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
       return titleList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return map[titleList[groupPosition]]!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val groupItemView:View
        if (convertView==null){
            groupItemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_parent,parent,false)
        }else groupItemView = convertView
        groupItemView.findViewById<TextView>(R.id.name_parent).text = titleList[groupPosition]
        return groupItemView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val childItemView:View
        if (convertView==null){
            childItemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_child,parent,false)
        }else childItemView = convertView

        val list = map[titleList[groupPosition]]
        val childList = list?.get(childPosition)
        childItemView.findViewById<TextView>(R.id.name_child).text = childList

        return childItemView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}