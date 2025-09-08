package com.ecotracker.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ecotracker.data.models.EmissionCategory
import com.ecotracker.databinding.FragmentCalculatorBinding
import com.ecotracker.viewmodel.CalculatorViewModel

class CalculatorFragment : Fragment() {
    
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var viewModel: CalculatorViewModel
    private var selectedCategory = EmissionCategory.TRANSPORT
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        
        setupObservers()
        setupUI()
        setupCategorySelection()
    }
    
    private fun setupObservers() {
        viewModel.calculationResult.observe(viewLifecycleOwner) { result ->
            binding.resultText.text = String.format("%.2f kg CO₂", result)
            binding.resultCard.visibility = View.VISIBLE
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.calculateButton.isEnabled = !isLoading
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun setupUI() {
        binding.calculateButton.setOnClickListener {
            calculateEmissions()
        }
        
        binding.saveButton.setOnClickListener {
            saveCalculation()
        }
    }
    
    private fun setupCategorySelection() {
        binding.transportChip.setOnClickListener { selectCategory(EmissionCategory.TRANSPORT) }
        binding.energyChip.setOnClickListener { selectCategory(EmissionCategory.ENERGY) }
        binding.foodChip.setOnClickListener { selectCategory(EmissionCategory.FOOD) }
        binding.consumptionChip.setOnClickListener { selectCategory(EmissionCategory.CONSUMPTION) }
        
        // Set initial selection
        selectCategory(EmissionCategory.TRANSPORT)
    }
    
    private fun selectCategory(category: EmissionCategory) {
        selectedCategory = category
        
        // Update chip selection
        binding.transportChip.isChecked = category == EmissionCategory.TRANSPORT
        binding.energyChip.isChecked = category == EmissionCategory.ENERGY
        binding.foodChip.isChecked = category == EmissionCategory.FOOD
        binding.consumptionChip.isChecked = category == EmissionCategory.CONSUMPTION
        
        // Update input fields based on category
        updateInputFields(category)
    }
    
    private fun updateInputFields(category: EmissionCategory) {
        when (category) {
            EmissionCategory.TRANSPORT -> {
                binding.inputLabel.text = "Distância (km)"
                binding.inputHint.text = "Ex: 50"
            }
            EmissionCategory.ENERGY -> {
                binding.inputLabel.text = "Consumo de Energia (kWh)"
                binding.inputHint.text = "Ex: 300"
            }
            EmissionCategory.FOOD -> {
                binding.inputLabel.text = "Quantidade (kg)"
                binding.inputHint.text = "Ex: 2"
            }
            EmissionCategory.CONSUMPTION -> {
                binding.inputLabel.text = "Valor (R$)"
                binding.inputHint.text = "Ex: 100"
            }
            else -> {}
        }
    }
    
    private fun calculateEmissions() {
        val valueText = binding.valueInput.text.toString()
        if (valueText.isEmpty()) {
            Toast.makeText(context, "Por favor, insira um valor", Toast.LENGTH_SHORT).show()
            return
        }
        
        val value = valueText.toDoubleOrNull()
        if (value == null || value <= 0) {
            Toast.makeText(context, "Por favor, insira um valor válido", Toast.LENGTH_SHORT).show()
            return
        }
        
        viewModel.calculateEmissions(selectedCategory, value)
    }
    
    private fun saveCalculation() {
        val description = binding.descriptionInput.text.toString()
        viewModel.saveCalculation(description)
        Toast.makeText(context, "Cálculo salvo com sucesso!", Toast.LENGTH_SHORT).show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

