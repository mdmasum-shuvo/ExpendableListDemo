package com.masum.listdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.listdemo.R
import com.masum.listdemo.databinding.ItemDemoBinding
import com.masum.listdemo.databinding.ItemDemoExpendedBinding
import com.masum.listdemo.model.MainModel

class MainListAdapter(private val context: Context, private val list: List<MainModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val typeNormalList = 1
    private val typeExpendedList = 2
    private val itemClick: ItemClick?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == typeExpendedList) {
            // for expended list
            val binding: ItemDemoExpendedBinding
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_demo_expended,
                parent,
                false
            )
            ExpViewHolder(binding)
        } else {
            // for normal list
            val binding: ItemDemoBinding
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_demo,
                parent,
                false
            )
            ViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == typeExpendedList) {
            showExpendedList((holder as ExpViewHolder).binding, list[position], position)
        } else {
            showNormalList((holder as ViewHolder).binding, list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].type == "chapter") {
            typeExpendedList
        } else {
            typeNormalList
        }
    }

    internal inner class ViewHolder(var binding: ItemDemoBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    internal inner class ExpViewHolder(var binding: ItemDemoExpendedBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )

    private fun showExpendedList(
        binding: ItemDemoExpendedBinding,
        mainModel: MainModel,
        position: Int
    ) {
        binding.tvTitle.text = mainModel.name
        if (mainModel.isExpended) {
            binding.indicator.setImageResource(R.drawable.arrow_down)
            // init another recyclerview and list
            binding.rvInnerRecycler.visibility = View.VISIBLE
            val subListAdapter = SubListAdapter(context, mainModel.subModels)
            binding.rvInnerRecycler.adapter = subListAdapter
        } else {
            binding.indicator.setImageResource(R.drawable.arrow_right)
            // close inner recyclerview and list
            binding.rvInnerRecycler.visibility = View.GONE
        }
        binding.parent.setOnClickListener {
            if (itemClick != null) {
                var isExpanded = mainModel.isExpended
                isExpanded = !isExpanded
                mainModel.isExpended = isExpanded
                itemClick.itemClick(mainModel)
                notifyItemChanged(position)
            }
        }
    }

    private fun showNormalList(binding: ItemDemoBinding, mainModel: MainModel) {
        binding.tvTitle.text = mainModel.name
        binding.parent.setOnClickListener { itemClick?.itemClick(mainModel) }
    }

    interface ItemClick {
        fun itemClick(mainModel: MainModel?)
    }

    init {
        itemClick = context as ItemClick
    }
}