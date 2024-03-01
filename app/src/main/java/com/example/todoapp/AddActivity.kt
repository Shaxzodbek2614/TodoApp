package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.todoapp.adapters.MySpinerAdapter
import com.example.todoapp.databinding.ActivityAddBinding
import com.example.todoapp.models.Model
import com.example.todoapp.models.Spinner
import com.example.todoapp.utils.MySharedPreference

private const val TAG = "AddActivity"
class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private lateinit var userArray:ArrayList<Spinner>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadData()
        MySharedPreference.init(this)

        val mySpinerAdapter = MySpinerAdapter(userArray)
        binding.addSpiner.adapter = mySpinerAdapter

        binding.btnSave.setOnClickListener {
            val toDoName = binding.addName.text.toString().trim()
            val toDoDescription = binding.addDescription.text.toString().trim()
            val toDoCreateData = binding.createDate.text.toString().trim()
            val toDoDedline = binding.dedline.text.toString().trim()
            val degree = userArray[binding.addSpiner.selectedItemPosition]

            if (toDoName!="" && toDoCreateData!="" && toDoDedline!="" && toDoDescription!=""){
                val myList = MySharedPreference.list
                myList.add(Model(toDoName,toDoDescription,degree,toDoCreateData,toDoDedline,"Open"))
                MySharedPreference.list = myList
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                finish()
            }else{
                Toast.makeText(this, "Error because editText is empty", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun loadData() {
        userArray = ArrayList()
        userArray.add(Spinner("To do deegre",-1))
        userArray.add(Spinner("Urgent",R.drawable.red_flag))
        userArray.add(Spinner("High",R.drawable.yellow_flag))
        userArray.add(Spinner( "Normal",R.drawable.blue_flag))
        userArray.add(Spinner( "Low",R.drawable.gray_flag))
    }

}