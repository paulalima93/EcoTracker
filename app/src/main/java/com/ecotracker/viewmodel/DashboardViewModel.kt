package com.ecotracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecotracker.data.models.EmissionSummary
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    
    private val _totalEmissions = MutableLiveData<Double>()
    val totalEmissions: LiveData<Double> = _totalEmissions
    
    private val _monthlyEmissions = MutableLiveData<Double>()
    val monthlyEmissions: LiveData<Double> = _monthlyEmissions
    
    private val _lastMonthEmissions = MutableLiveData<Double>()
    val lastMonthEmissions: LiveData<Double> = _lastMonthEmissions
    
    private val _emissionsByCategory = MutableLiveData<List<EmissionSummary>>()
    val emissionsByCategory: LiveData<List<EmissionSummary>> = _emissionsByCategory
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    init {
        loadDashboardData()
    }
    
    fun refreshData() {
        loadDashboardData()
    }
    
    private fun loadDashboardData() {
        viewModelScope.launch {
            _isLoading.value = true
            
            try {
                // TODO: Replace with actual repository calls
                // For now, using mock data
                loadMockData()
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private fun loadMockData() {
        // Mock data for demonstration
        _totalEmissions.value = 125.50
        _monthlyEmissions.value = 45.30
        _lastMonthEmissions.value = 52.80
        
        // Mock category data
        val mockCategories = listOf(
            EmissionSummary(
                category = com.ecotracker.data.models.EmissionCategory.TRANSPORT,
                totalEmissions = 60.0,
                percentage = 48.0f
            ),
            EmissionSummary(
                category = com.ecotracker.data.models.EmissionCategory.ENERGY,
                totalEmissions = 35.0,
                percentage = 28.0f
            ),
            EmissionSummary(
                category = com.ecotracker.data.models.EmissionCategory.FOOD,
                totalEmissions = 20.0,
                percentage = 16.0f
            ),
            EmissionSummary(
                category = com.ecotracker.data.models.EmissionCategory.CONSUMPTION,
                totalEmissions = 10.5,
                percentage = 8.0f
            )
        )
        
        _emissionsByCategory.value = mockCategories
    }
}

