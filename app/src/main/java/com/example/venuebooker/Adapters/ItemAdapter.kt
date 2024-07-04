package com.example.venuebooker.Adapters

import Activities.SportsListActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.venuebooker.ApiModels.Response
import com.example.venuebooker.databinding.TurfsItemviewBinding

class ItemAdapter(val customers: List<Response>, val context: Context) :
    RecyclerView.Adapter<ItemAdapter.Holder>() {
    class Holder(val binding: TurfsItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            TurfsItemviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return customers.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.details.text = "Turfs"
        val item = customers.get(position)
        holder.binding.name.setText(item.name)

        holder.binding.details.setOnClickListener {
            context.startActivity(Intent(context, SportsListActivity::class.java).putExtra("id",position))
        }
    }
}