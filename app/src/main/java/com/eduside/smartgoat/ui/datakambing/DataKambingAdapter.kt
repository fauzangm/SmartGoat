package com.eduside.smartgoat.ui.datakambing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduside.smartgoat.R
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.databinding.ItemListDataKambingBinding
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class DataKambingAdapter @Inject constructor() :
    ListAdapter<DatakambingVo, DataKambingAdapter.ViewHolder>(ListDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListDataKambingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvNamakambing.text = data.ras
        holder.binding.tvNokambing.text = data.id.toString()
//        Glide
//            .with(holder.itemView.context)
//            .load(data.image)
//            .centerCrop()
//            .placeholder(R.drawable.ic_defaultimage)
//            .into(holder.binding.imgKambing)
        holder.binding.cvContainer.setOnClickListener {
            EventBus.getDefault().post(ItemDataKambingEvent(data))
        }
    }

    class ViewHolder(itemBinding: ItemListDataKambingBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var binding: ItemListDataKambingBinding = itemBinding
    }

    class ListDiffUtill : DiffUtil.ItemCallback<DatakambingVo>() {
        override fun areItemsTheSame(oldItem: DatakambingVo, newItem: DatakambingVo): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: DatakambingVo, newItem: DatakambingVo): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}