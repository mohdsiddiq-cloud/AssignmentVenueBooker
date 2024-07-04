package com.example.venuebooker.Adapters

import Activities.BookingActivity
import Activities.SlotsActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.venuebooker.ApiModels.ResponseX
import com.example.venuebooker.ApiModels.Turf
import com.example.venuebooker.databinding.SlottTimeItemviewBinding
import com.example.venuebooker.databinding.TurfsItemviewBinding

class ItemAdapter4(val customers: List<ResponseX>, val context: Context,val id:Int) :
    RecyclerView.Adapter<ItemAdapter4.Holder>() {
    class Holder(val binding: SlottTimeItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            SlottTimeItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = customers.get(position)
        holder.binding.startDate.setText(item.start_time.substring(10))
        holder.binding.endDate.setText(item.end_time.substring(10))
        holder.binding.price.setText(item.base_price)

        if(item.available){
            holder.binding.btn1.visibility = View.VISIBLE
        }
        else{
            holder.binding.btn2.visibility = View.VISIBLE
        }

        holder.binding.btn1.setOnClickListener {
            context.startActivity(Intent(context, BookingActivity::class.java).putExtra("id",id).putExtra("startTime",item.start_time).putExtra("endTime",item.end_time))
        }
        holder.binding.btn2.setOnClickListener {

        }
    }
}