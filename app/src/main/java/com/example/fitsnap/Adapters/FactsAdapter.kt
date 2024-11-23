package com.example.fitsnap.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitsnap.databinding.FactitemBinding

class FactsAdapter(private val facts:List<String>):RecyclerView.Adapter<FactsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FactitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (facts.isEmpty()){
            return 0
        }else{
            return facts.size
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(facts[position])
    }
    inner class ViewHolder(private val binding: FactitemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(fact: String) {
            binding.fact.text = fact
        }

    }
}