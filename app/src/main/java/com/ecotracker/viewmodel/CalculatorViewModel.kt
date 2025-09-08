package com.ecotracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ecotracker.data.database.EcoTrackerDatabase
import com.ecotracker.data.models.EmissionCategory
import com.ecotracker.data.models.EmissionRecord
import com.ecotracker.data.repository.EmissionRepository
import kotlinx.coroutines.launch
import java.util.Date

class CalculatorViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = EmissionRepository()
    private val database = EcoTrackerDatabase.getDatabase(application)
    private val emissionDao = database.emissionDao()
    
    private val _calculationResult = MutableLiveData<Double>()
    val calculationResult: LiveData<Double> = _calculationResult
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    private var currentCalculation: EmissionRecord? = null
    
    fun calculateEmissions(category: EmissionCategory, value: Double) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = ""
            
            try {
                val result = when (category) {
                    EmissionCategory.TRANSPORT -> repository.calculateVehicleEmissions(value)
                    EmissionCategory.ENERGY -> repository.calculateElectricityEmissions(value)
                    EmissionCategory.FOOD -> repository.calculateFoodEmissions("average", value)
                    EmissionCategory.CONSUMPTION -> repository.calculateConsumptionEmissions(value)
                    EmissionCategory.OTHER -> Result.success(value * 1.0)
                }
                
                result.fold(
                    onSuccess = { emissions ->
                        currentCalculation = EmissionRecord(
                            category = category,
                            activity = getActivityName(category),
                            value = value,
                            unit = getUnit(category),
                            carbonEmission = emissions,
                            date = Date()
                        )
                        _calculationResult.value = emissions
                    },
                    onFailure = { exception ->
                        _errorMessage.value = "Erro ao calcular emissões: ${exception.message}"
                    }
                )
            } catch (e: Exception) {
                _errorMessage.value = "Erro inesperado: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun saveCalculation(description: String) {
        currentCalculation?.let { calculation ->
            viewModelScope.launch {
                try {
                    val updatedCalculation = calculation.copy(description = description)
                    emissionDao.insertEmission(updatedCalculation)
                } catch (e: Exception) {
                    _errorMessage.value = "Erro ao salvar cálculo: ${e.message}"
                }
            }
        }
    }
    
    private fun getActivityName(category: EmissionCategory): String {
        return when (category) {
            EmissionCategory.TRANSPORT -> "Transporte"
            EmissionCategory.ENERGY -> "Energia"
            EmissionCategory.FOOD -> "Alimentação"
            EmissionCategory.CONSUMPTION -> "Consumo"
            EmissionCategory.OTHER -> "Outros"
        }
    }
    
    private fun getUnit(category: EmissionCategory): String {
        return when (category) {
            EmissionCategory.TRANSPORT -> "km"
            EmissionCategory.ENERGY -> "kWh"
            EmissionCategory.FOOD -> "kg"
            EmissionCategory.CONSUMPTION -> "R$"
            EmissionCategory.OTHER -> "unidade"
        }
    }
}

