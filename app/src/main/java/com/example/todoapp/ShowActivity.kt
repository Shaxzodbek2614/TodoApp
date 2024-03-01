package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.adapters.ExpandableAdapter
import com.example.todoapp.databinding.ActivityShowBinding
import com.example.todoapp.utils.MySharedPreference

private const val TAG = "ShowActivity"
class ShowActivity : AppCompatActivity() {
    private val binding by lazy { ActivityShowBinding.inflate(layoutInflater) }
    private lateinit var map: HashMap<String, ArrayList<String>>
    private lateinit var titleList:ArrayList<String>
    lateinit var expandableAdapter:ExpandableAdapter
    private lateinit var openArray:ArrayList<String>
    private lateinit var developmentArray:ArrayList<String>
    private lateinit var uploadingArray:ArrayList<String>
    private lateinit var rejectArray:ArrayList<String>
    private lateinit var closedArray:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        MySharedPreference.init(this)
        keshdanArrayga()
        expandableAdapter = ExpandableAdapter(titleList,map)
        binding.expandable.setAdapter(expandableAdapter)

        binding.expandable.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val intent = Intent(this, ChildActivity::class.java)
            intent.putExtra("name", map[titleList[groupPosition]]?.get(childPosition))
            intent.putExtra("name1",titleList[groupPosition])
            intent.putExtra("name2",childPosition)
            startActivity(intent)
            true
        }

    }


    private fun keshdanArrayga() {
        map = HashMap()
        titleList = ArrayList()
        titleList.add("Open")
        titleList.add("Development")
        titleList.add("Uploading")
        titleList.add("Reject")
        titleList.add("Close")

        openArray = ArrayList()
        developmentArray = ArrayList()
        uploadingArray = ArrayList()
        rejectArray = ArrayList()
        closedArray = ArrayList()

        val planArray =  MySharedPreference.list
        for (model in planArray) {
            if (model.level=="Open") openArray.add(model.name.toString())
            if (model.level=="Development") developmentArray.add(model.name.toString())
            if (model.level=="Uploading") uploadingArray.add(model.name.toString())
            if (model.level== "Reject") rejectArray.add(model.name.toString())
            if (model.level=="Close") closedArray.add(model.name.toString())
        }


        map[titleList[0]] = openArray
        map[titleList[1]] = developmentArray
        map[titleList[2]] = uploadingArray
        map[titleList[3]] = rejectArray
        map[titleList[4]] = closedArray

    }


}