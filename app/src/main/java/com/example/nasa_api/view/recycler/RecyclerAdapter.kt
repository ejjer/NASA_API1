package com.example.nasa_api.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasa_api.R
import com.example.nasa_api.databinding.ActivityRecyclerItemEarthBinding
import com.example.nasa_api.databinding.ActivityRecyclerItemHeaderBinding
import com.example.nasa_api.databinding.ActivityRecyclerItemMarsBinding
import com.example.nasa_api.view.recycler.diffUttil.DiffUtilCallback


class RecyclerAdapter(
    private var listData: MutableList<Pair<Data,Boolean>>,
    val callbackAdd: AddItem,
    val callbackRemove: RemoveItem
) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(),ItemTouchHelperAdapter {


    fun setListDataForDiffUtil(listDataNew: MutableList<Pair<Data,Boolean>>) {
        val diff = DiffUtil.calculateDiff(DiffUtilCallback(listData, listDataNew))
        diff.dispatchUpdatesTo(this)
        listData= listDataNew
    }

    fun setListDataRemove(listDataNew: MutableList<Pair<Data, Boolean>>, position: Int) {
        listData = listDataNew
        notifyItemRemoved(position)
    }

    fun setListDataAdd(listDataNew: MutableList<Pair<Data,Boolean>>, position: Int) {
        listData = listDataNew
        notifyItemInserted(position)
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
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

//    override fun onBindViewHolder(
//        holder: BaseViewHolder,
//        position: Int,
//        payloads: MutableList<Any>
//    ) {
//        if (payloads.isEmpty()) {
//            super.onBindViewHolder(holder, position, payloads)
//        } else {
//            val createCombinedPayload = createCombinedPayload(payloads as List<Change<Pair<Data, Boolean>> >)
//            if(createCombinedPayload.newData.first.name!=createCombinedPayload.oldData.first.name)
//                holder.itemView.findViewById<TextView>(R.id.name).text =createCombinedPayload.newData.first.name
//        }
//    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class MarsViewHolder(val binding: ActivityRecyclerItemMarsBinding) :
        BaseViewHolder(binding.root),ItemTouchHelperViewHolder {
        override fun bind(data: Pair<Data,Boolean>) {
            binding.name.text = data.first.name

            binding.addItemImageView.setOnClickListener {
                callbackAdd.add(layoutPosition)
            }
            binding.removeItemImageView.setOnClickListener {
                callbackRemove.remove(layoutPosition)
            }
            binding.moveItemUp.setOnClickListener {

                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition - 1, this)

                }
                notifyItemMoved(layoutPosition, layoutPosition - 1)
            }
            binding.moveItemDown.setOnClickListener {

                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition + 1, this)

                }
                notifyItemMoved(layoutPosition, layoutPosition + 1)
            }
            binding.marsDescriptionTextView.visibility = if(listData[layoutPosition].second)View.VISIBLE else View.GONE
            binding.marsImageView.setOnClickListener{
                listData[layoutPosition] = listData[layoutPosition].let{
                    it.first to !it.second
                }

                notifyItemChanged(layoutPosition)
            }

        }

        override fun onItemSelect() {
            binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context,R.color.Grey))
        }

        override fun onItemClear() {
            binding.root.setBackgroundColor(0)

        }
    }


    class EarthViewHolder(val binding: ActivityRecyclerItemEarthBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>) {
            binding.name.text = data.first.name
        }
    }


    class HeaderViewHolder(val binding: ActivityRecyclerItemHeaderBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>) {
            binding.name.text = data.first.name
        }
    }

    abstract class BaseViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Data,Boolean>)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        callbackRemove.remove(position)
    }


}