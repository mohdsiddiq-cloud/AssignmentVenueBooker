package com.example.venuebooker.Adapters

import Activities.SportsTurfsListActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.venuebooker.ApiModels.Category
import com.example.venuebooker.databinding.TurfsItemviewBinding
import java.io.Serializable


class ItemAdapter2(val customers: List<Category>, val context: Context,val id:Int) :
    RecyclerView.Adapter<ItemAdapter2.Holder>() {
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
        val item = customers.get(position)
        holder.binding.name.setText(item.name)

        holder.binding.details.setOnClickListener {

            context.startActivity(Intent(context, SportsTurfsListActivity::class.java).putExtra("id1",id).putExtra("id2",position))
        }
    }
}