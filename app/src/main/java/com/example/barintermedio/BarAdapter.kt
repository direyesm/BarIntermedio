package com.example.barintermedio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barintermedio.database.Bar
import kotlinx.android.synthetic.main.bar_item_list.view.*
import kotlinx.android.synthetic.main.fragment_first.view.*

class BarAdapter(var mPassTheData: PassTheData) : RecyclerView.Adapter<BarAdapter.BarViewHolder>(){

    private var dataList = emptyList<Bar>()

    fun upDataList(mDataList: List<Bar>){
        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class BarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val barText = itemView.barTex
        val cant = itemView.cant
        val valor = itemView.valor
        val itemView = itemView.setOnClickListener(this)


        override fun onClick(p0: View?) {
           mPassTheData.passTheData(dataList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bar_item_list, parent, false)
        return BarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BarViewHolder, position: Int) {
        val mBar: Bar = dataList[position]
        holder.barText.text = mBar.name
        holder.cant.inputType = mBar.unit
        holder.valor.inputType = mBar.price.toInt()
    }

    override fun getItemCount() = dataList.size

    interface PassTheData{
        fun passTheData(mBar: Bar)
    }
}