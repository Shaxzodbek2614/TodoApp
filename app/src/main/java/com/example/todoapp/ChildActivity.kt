package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todoapp.databinding.ActivityChildBinding
import com.example.todoapp.models.Model
import com.example.todoapp.utils.MySharedPreference

class ChildActivity : AppCompatActivity() {
    private val binding by lazy { ActivityChildBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        MySharedPreference.init(this)
        var model1 = Model()
        val name = intent.getStringExtra("name")
        val namegroup = intent.getStringExtra("name1")
        val childPosition = intent.getIntExtra("name2",0)
        val list = MySharedPreference.list
        var count = 0
        var index = -1
        for (model in list) {
            if (model.level==namegroup) {
                if (model.name == name && childPosition==count) {
                    model1 = model
                    index = list.indexOf(model)
                    binding.childName.text = model.name
                    binding.desciption.text = model.description
                    binding.date.text = model.createData
                    binding.dedline.text = model.dedline
                    binding.name.text = model.degree?.name
                    binding.img.setImageResource(model.degree!!.image)
                    when (model.level) {
                        "Open" -> binding.open.isChecked = true
                        "Development" -> binding.development.isChecked = true
                        "Uploading" -> binding.uploading.isChecked = true
                        "Reject" -> binding.reject.isChecked = true
                        "Close" -> binding.closed.isChecked = true
                    }
                    break
                }
                count++
            }
        }
        binding.btnOk.setOnClickListener {
            var rad = ""
            if (binding.open.isChecked) rad = "Open"
            if (binding.development.isChecked) rad = "Development"
            if (binding.uploading.isChecked) rad = "Uploading"
            if (binding.closed.isChecked) rad = "Close"
            if (binding.reject.isChecked) rad = "Reject"

            model1.level = rad
            list[index] = model1
            MySharedPreference.list = list
            Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}