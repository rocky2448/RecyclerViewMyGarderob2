package com.example.recyclerviewmygarderob2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewmygarderob.R

class CustomAdapter(val clothesss: MutableList<Clothes>) :
RecyclerView.Adapter<CustomAdapter.ClothesViewHolder>() {

    private var onClothesClickListener: OnClothesClickListener? = null

    interface OnClothesClickListener {
        fun OnClothesClick (clothes: Clothes, position: Int)
    }

    class ClothesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        val imageIV: ImageView = itemView.findViewById(R.id.imageClothesIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ClothesViewHolder(itemView)
    }

    override fun getItemCount() = clothesss.size

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        val clothes = clothesss[position]
        holder.nameTV.text = clothes.name
        holder.imageIV.setImageResource(clothes.image)
        holder.itemView.setOnClickListener {
            if (onClothesClickListener != null) {
                onClothesClickListener!!.OnClothesClick(clothes, position)
            }
        }
    }

    fun setOnClothesClickListener(onClothesClickListener: OnClothesClickListener) {
        this.onClothesClickListener = onClothesClickListener
    }
}