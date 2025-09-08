package com.ecotracker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ecotracker.databinding.FragmentDashboardBinding
import com.ecotracker.viewmodel.DashboardViewModel

class DashboardFragment : Fragment() {
    
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: DashboardViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        
        setupObservers()
        setupUI()
    }
    
    private fun setupObservers() {
        viewModel.totalEmissions.observe(viewLifecycleOwner) { emissions ->
            binding.totalEmissionsText.text = String.format("%.2f kg CO₂", emissions)
        }
        
        viewModel.monthlyEmissions.observe(viewLifecycleOwner) { emissions ->
            binding.monthlyEmissionsText.text = String.format("%.2f kg CO₂", emissions)
        }
        
        viewModel.emissionsByCategory.observe(viewLifecycleOwner) { categories ->
            updateCategoryChart(categories)
        }
    }
    
    private fun setupUI() {
        binding.refreshButton.setOnClickListener {
            viewModel.refreshData()
        }
    }
    
    private fun updateCategoryChart(categories: List<Any>) {
        // TODO: Implement chart update logic
        // This will be implemented when we add the chart library integration
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

