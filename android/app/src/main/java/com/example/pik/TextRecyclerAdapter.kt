package com.example.pik

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.no_consultations_message_item.view.*

class TextRecyclerAdapter(private val message: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TextRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.no_consultations_message_item, parent, false) as ConstraintLayout)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.message.text = message
    }
}

class TextRecyclerViewHolder(item: ConstraintLayout): RecyclerView.ViewHolder(item)