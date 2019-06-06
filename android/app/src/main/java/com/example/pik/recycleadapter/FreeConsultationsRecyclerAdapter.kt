package com.example.pik.recycleadapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pik.R
import com.example.pik.REST.entity.Consultation
import kotlinx.android.synthetic.main.free_consultation_item.view.*
import java.time.format.DateTimeFormatter


class FreeConsultationsRecyclerAdapter (
    private val consultations: List<Consultation>,
    private val actionListener: ActionListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context

    interface ActionListener {
        fun reserve(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.free_consultation_item,
                parent,
                false
            ) as ConstraintLayout
        )
    }

    override fun getItemCount(): Int {
        return consultations.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val consultation = consultations[position]
        holder.itemView.elevation = 20.0f
        holder.itemView.person.text = "${consultation.tutor.name} ${consultation.tutor.surname}"
        holder.itemView.day.text = consultation.date.format(DateTimeFormatter.ISO_DATE)
        holder.itemView.start_time.text =
            "${consultation.consultationStartTime.format(DateTimeFormatter.ISO_LOCAL_TIME)} - ${consultation.consultationEndTime.format(DateTimeFormatter.ISO_LOCAL_TIME)}"
        holder.itemView.reservation_button.setOnClickListener { v ->
            actionListener.reserve(consultation.id)
        }
    }
}

class RecyclerViewHolder(item: ConstraintLayout) : RecyclerView.ViewHolder(item)


