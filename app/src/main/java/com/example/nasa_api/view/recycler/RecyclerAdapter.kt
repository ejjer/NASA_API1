package com.example.nasa_api.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_api.databinding.ActivityRecyclerItemEarthBinding
import com.example.nasa_api.databinding.ActivityRecyclerItemHeaderBinding
import com.example.nasa_api.databinding.ActivityRecyclerItemMarsBinding


class RecyclerAdapter(
    private var listData: List<Data>,
    val callbackAdd: AddItem,
    val callbackRemove: RemoveItem
) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {


    fun setListDataRemove(listDataNew: List<Data>, position: Int) {
        listData = listDataNew
        notifyItemRemoved(position)
    }

    fun setListDataAdd(listDataNew: List<Data>, position: Int) {
        listData = listDataNew
        notifyItemInserted(position)
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        return when (viewType) {
            TYPE_EARTH -> {
                val binding =
                    ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(binding)
            }
            TYPE_MARS -> {
                val binding =
                    ActivityRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding)

            }
            else -> {
                val binding =
                    ActivityRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(binding)
            }
        }


    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class MarsViewHolder(val binding: ActivityRecyclerItemMarsBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Data) {
            binding.name.text = data.name

            binding.addItemImageView.setOnClickListener {
                callbackAdd.add(layoutPosition)
            }
            binding.removeItemImageView.setOnClickListener {
                callbackRemove.remove(layoutPosition)
            }

        }
    }


    class EarthViewHolder(val binding: ActivityRecyclerItemEarthBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Data) {
            binding.name.text = data.name
        }
    }


    class HeaderViewHolder(val binding: ActivityRecyclerItemHeaderBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Data) {
            binding.name.text = data.name
        }
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Data)
    }

}