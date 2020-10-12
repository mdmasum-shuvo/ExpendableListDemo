package com.masum.listdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.masum.listdemo.R
import com.masum.listdemo.databinding.ItemDemoBinding
import com.masum.listdemo.databinding.SubItemBinding
import com.masum.listdemo.model.SubModel

internal class SubListAdapter(private val context: Context, private val models: List<SubModel>) :
    RecyclerView.Adapter<SubListAdapter.ViewHolder>() {
    private val itemClick: ItemClick?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemDemoBinding
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_demo,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text=models[position].name
        holder.binding.data=models[position]
        holder.binding.parent.setOnClickListener { itemClick?.innerItemClick(models[position]) }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    internal inner class ViewHolder(var binding: ItemDemoBinding) : RecyclerView.ViewHolder(
        binding.root
    )

    interface ItemClick {
        fun innerItemClick(model: SubModel?)
    }

    init {
        itemClick = context as ItemClick
    }
}