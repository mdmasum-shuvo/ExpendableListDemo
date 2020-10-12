package com.masum.listdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.masum.listdemo.adapter.MainListAdapter
import com.masum.listdemo.adapter.SubListAdapter
import com.masum.listdemo.databinding.ActivityMainBinding
import com.masum.listdemo.model.SubModel
import com.masum.listdemo.model.MainModel

class MainActivity : AppCompatActivity(), MainListAdapter.ItemClick, SubListAdapter.ItemClick {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        val list: MutableList<MainModel> = java.util.ArrayList<MainModel>()
        for (i in 1..20) {
            val model = MainModel()
            model.id = i
            model.name = "Demo Name $i"
            if (i % 3 == 0) {
                model.type = "chapter"
                val subs: MutableList<SubModel> = java.util.ArrayList<SubModel>()
                for (j in 1..3) {
                    val subModel = SubModel()
                    subModel.id = j
                    subModel.name = "Inner Name $j"
                    subModel.type = "Type"
                    subModel.subTitle = "Inner Sub Name $j"
                    subModel.img = "Type"
                    subs.add(subModel)
                }
                model.subModels = subs
            } else if (i % 2 == 0) {
                model.type = "audio"

            } else {
                model.type = "video"
            }
            list.add(model)
        }
        val adapter = MainListAdapter(this, list)
        binding.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun itemClick(mainModel: MainModel?) {
        Toast.makeText(
            this,
            mainModel!!.name.toString() + " , " + mainModel.type,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun innerItemClick(model: SubModel?) {
        Toast.makeText(
            this,
            model!!.name.toString() + " , " + model.type,
            Toast.LENGTH_SHORT
        ).show()
    }
}