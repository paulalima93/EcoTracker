package com.ecotracker.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecotracker.R
import com.ecotracker.data.models.EmissionCategory
import com.ecotracker.data.models.ImpactLevel
import com.ecotracker.data.models.SustainabilityTip
import com.ecotracker.databinding.ItemTipBinding

class TipsAdapter : ListAdapter<SustainabilityTip, TipsAdapter.TipViewHolder>(TipDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipViewHolder {
        val binding = ItemTipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TipViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: TipViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    class TipViewHolder(private val binding: ItemTipBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(tip: SustainabilityTip) {
            binding.tipTitle.text = tip.title
            binding.tipDescription.text = tip.description
            
            // Set category color
            val categoryColor = when (tip.category) {
                EmissionCategory.TRANSPORT -> R.color.chart_transport
                EmissionCategory.ENERGY -> R.color.chart_energy
                EmissionCategory.FOOD -> R.color.chart_food
                EmissionCategory.CONSUMPTION -> R.color.chart_consumption
                else -> R.color.chart_other
            }
            
            binding.categoryIndicator.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, categoryColor)
            )
            
            // Set impact level
            val impactText = when (tip.impactLevel) {
                ImpactLevel.LOW -> "Baixo Impacto"
                ImpactLevel.MEDIUM -> "Médio Impacto"
                ImpactLevel.HIGH -> "Alto Impacto"
            }
            
            binding.impactLevel.text = impactText
            
            val impactColor = when (tip.impactLevel) {
                ImpactLevel.LOW -> R.color.success
                ImpactLevel.MEDIUM -> R.color.warning
                ImpactLevel.HIGH -> R.color.error
            }
            
            binding.impactLevel.setTextColor(
                ContextCompat.getColor(binding.root.context, impactColor)
            )
        }
    }
    
    class TipDiffCallback : DiffUtil.ItemCallback<SustainabilityTip>() {
        override fun areItemsTheSame(oldItem: SustainabilityTip, newItem: SustainabilityTip): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: SustainabilityTip, newItem: SustainabilityTip): Boolean {
            return oldItem == newItem
        }
    }
}

