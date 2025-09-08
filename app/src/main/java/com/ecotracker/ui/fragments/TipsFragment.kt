package com.ecotracker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecotracker.data.models.EmissionCategory
import com.ecotracker.data.models.ImpactLevel
import com.ecotracker.data.models.SustainabilityTip
import com.ecotracker.databinding.FragmentTipsBinding
import com.ecotracker.ui.adapters.TipsAdapter

class TipsFragment : Fragment() {
    
    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var tipsAdapter: TipsAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadTips()
    }
    
    private fun setupRecyclerView() {
        tipsAdapter = TipsAdapter()
        binding.tipsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tipsAdapter
        }
    }
    
    private fun loadTips() {
        val tips = getMockTips()
        tipsAdapter.submitList(tips)
    }
    
    private fun getMockTips(): List<SustainabilityTip> {
        return listOf(
            SustainabilityTip(
                id = 1,
                title = "Use transporte público",
                description = "Opte por ônibus, metrô ou trem em vez do carro particular. Isso pode reduzir suas emissões de transporte em até 45%.",
                category = EmissionCategory.TRANSPORT,
                impactLevel = ImpactLevel.HIGH
            ),
            SustainabilityTip(
                id = 2,
                title = "Desligue aparelhos da tomada",
                description = "Aparelhos em standby consomem energia. Desligar da tomada pode economizar até 10% na conta de luz.",
                category = EmissionCategory.ENERGY,
                impactLevel = ImpactLevel.MEDIUM
            ),
            SustainabilityTip(
                id = 3,
                title = "Reduza o consumo de carne",
                description = "A produção de carne bovina gera muitas emissões. Reduza o consumo para 2-3 vezes por semana.",
                category = EmissionCategory.FOOD,
                impactLevel = ImpactLevel.HIGH
            ),
            SustainabilityTip(
                id = 4,
                title = "Compre produtos locais",
                description = "Produtos locais têm menor pegada de carbono no transporte e apoiam a economia local.",
                category = EmissionCategory.CONSUMPTION,
                impactLevel = ImpactLevel.MEDIUM
            ),
            SustainabilityTip(
                id = 5,
                title = "Use lâmpadas LED",
                description = "Lâmpadas LED consomem até 80% menos energia que as incandescentes e duram muito mais.",
                category = EmissionCategory.ENERGY,
                impactLevel = ImpactLevel.MEDIUM
            )
        )
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

