package com.eduside.smartgoat.ui.home.notifications

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduside.smartgoat.R
import com.eduside.smartgoat.data.remote.response.DataBeritaItem
import com.eduside.smartgoat.databinding.ItemBeritaBinding
import com.eduside.smartgoat.databinding.ItemListDataKambingBinding
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class NotificationAdapter @Inject constructor() :
    ListAdapter<DataBeritaItem, NotificationAdapter.ViewHolder>(ListDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBeritaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.tvJudul.text = data.judul
        holder.binding.tvDeskripsi.text = data.isi
        Log.e("fotodata",data.image.toString())
        val dataimage = "https://"+data.image.toString()

        Glide
            .with(holder.itemView.context)
            .load(dataimage)
            .fitCenter()
            .placeholder(R.drawable.ic_defaultimage)
            .into(holder.binding.imgBerita)
  
    }

    class ViewHolder(itemBinding: ItemBeritaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var binding: ItemBeritaBinding = itemBinding
    }

    class ListDiffUtill : DiffUtil.ItemCallback<DataBeritaItem>() {
        override fun areItemsTheSame(oldItem: DataBeritaItem, newItem: DataBeritaItem): Boolean {
            return newItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: DataBeritaItem, newItem: DataBeritaItem): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
    }
}