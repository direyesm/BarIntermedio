package com.example.barintermedio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.barintermedio.database.Bar
import kotlinx.android.synthetic.main.bar_item_list.view.*

class BarAdapter(var mPassTheData: PassTheData) : RecyclerView.Adapter<BarAdapter.BarViewHolder>(){

    private var dataList = emptyList<Bar>()

    fun updateDataList(mDataList: List<Bar>){
        dataList = mDataList
        notifyDataSetChanged()
    }

    inner class BarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val nomTex = itemView.textName
        val canTex = itemView.cantxt
        val valText = itemView.valortxt
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
        holder.nomTex.text = mBar.name
        holder.canTex.text = mBar.cantidad.toString()
        holder.valText.text = mBar.precio.toString()
    }

    override fun getItemCount() = dataList.size

    interface PassTheData{
        fun passTheData(mbar: Bar)
    }
}