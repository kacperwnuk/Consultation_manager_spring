package com.example.pik

import android.annotation.SuppressLint
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pik.REST.entity.Consultation
import kotlinx.android.synthetic.main.my_booked_consultation_item.view.*
import java.time.format.DateTimeFormatter

class MyBookedConsultationsRecyclerAdapter(private val consultations: List<Consultation>,
                                           private val actionListener: ActionListener):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ActionListener {
        fun cancelConsultation(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_booked_consultation_item, parent, false) as ConstraintLayout
        return MyBookedConsultationRecyclerViewHolder(item)
    }

    override fun getItemCount(): Int {
        return consultations.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val consultation = consultations[position]
        holder.itemView.elevation = 20.0f
        holder.itemView.person.text = consultation.tutor.username
        holder.itemView.day.text = consultation.date.format(DateTimeFormatter.ISO_DATE)
        holder.itemView.start_time.text =
            "${consultation.consultationStartTime.format(DateTimeFormatter.ISO_LOCAL_TIME)} - ${consultation.consultationEndTime.format(
                DateTimeFormatter.ISO_LOCAL_TIME)}"
        holder.itemView.cancellation_button.setOnClickListener { v ->
            actionListener.cancelConsultation(consultation.id)
        }
    }
}

class MyBookedConsultationRecyclerViewHolder(item: ConstraintLayout): RecyclerView.ViewHolder(item)