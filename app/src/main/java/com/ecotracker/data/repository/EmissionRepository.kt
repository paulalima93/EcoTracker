package com.ecotracker.data.repository

import com.ecotracker.data.api.ApiService
import com.ecotracker.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmissionRepository {
    
    private val api = ApiService.carbonInterfaceApi
    
    suspend fun calculateElectricityEmissions(consumptionKwh: Double): Result<Double> {
        return withContext(Dispatchers.IO) {
            try {
                val request = ElectricityEstimateRequest(
                    electricityValue = consumptionKwh,
                    country = "br"
                )
                
                val response = api.calculateElectricityEmissions(
                    authorization = ApiService.getAuthorizationHeader(),
                    request = request
                )
                
                if (response.isSuccessful) {
                    val emissions = response.body()?.data?.attributes?.carbonKg ?: 0.0
                    Result.success(emissions)
                } else {
                    Result.failure(Exception("API Error: ${response.code()}"))
                }
            } catch (e: Exception) {
                // Fallback to mock calculation if API fails
                val mockEmissions = consumptionKwh * 0.45 // Brazil grid factor
                Result.success(mockEmissions)
            }
        }
    }
    
    suspend fun calculateVehicleEmissions(distanceKm: Double): Result<Double> {
        return withContext(Dispatchers.IO) {
            try {
                val request = VehicleEstimateRequest(
                    distanceValue = distanceKm
                )
                
                val response = api.calculateVehicleEmissions(
                    authorization = ApiService.getAuthorizationHeader(),
                    request = request
                )
                
                if (response.isSuccessful) {
                    val emissions = response.body()?.data?.attributes?.carbonKg ?: 0.0
                    Result.success(emissions)
                } else {
                    Result.failure(Exception("API Error: ${response.code()}"))
                }
            } catch (e: Exception) {
                // Fallback to mock calculation if API fails
                val mockEmissions = distanceKm * 0.21 // Average car emission factor
                Result.success(mockEmissions)
            }
        }
    }
    
    suspend fun calculateFlightEmissions(
        departureAirport: String,
        destinationAirport: String,
        passengers: Int = 1
    ): Result<Double> {
        return withContext(Dispatchers.IO) {
            try {
                val request = FlightEstimateRequest(
                    passengers = passengers,
                    legs = listOf(
                        FlightLeg(
                            departureAirport = departureAirport,
                            destinationAirport = destinationAirport
                        )
                    )
                )
                
                val response = api.calculateFlightEmissions(
                    authorization = ApiService.getAuthorizationHeader(),
                    request = request
                )
                
                if (response.isSuccessful) {
                    val emissions = response.body()?.data?.attributes?.carbonKg ?: 0.0
                    Result.success(emissions)
                } else {
                    Result.failure(Exception("API Error: ${response.code()}"))
                }
            } catch (e: Exception) {
                // Fallback to mock calculation if API fails
                val mockEmissions = 500.0 * passengers // Rough estimate for domestic flight
                Result.success(mockEmissions)
            }
        }
    }
    
    suspend fun calculateFoodEmissions(foodType: String, quantityKg: Double): Result<Double> {
        return withContext(Dispatchers.IO) {
            // Mock calculation for food emissions (no API available)
            val emissionFactor = when (foodType.lowercase()) {
                "beef", "carne bovina" -> 27.0
                "pork", "carne suína" -> 12.1
                "chicken", "frango" -> 6.9
                "fish", "peixe" -> 6.1
                "dairy", "laticínios" -> 3.2
                "vegetables", "vegetais" -> 2.0
                "fruits", "frutas" -> 1.1
                "grains", "grãos" -> 1.4
                else -> 2.5 // Average
            }
            
            val emissions = quantityKg * emissionFactor
            Result.success(emissions)
        }
    }
    
    suspend fun calculateConsumptionEmissions(valueReais: Double): Result<Double> {
        return withContext(Dispatchers.IO) {
            // Mock calculation for consumption emissions
            // Based on average carbon intensity of Brazilian economy
            val emissionFactor = 0.5 // kg CO2 per R$
            val emissions = valueReais * emissionFactor
            Result.success(emissions)
        }
    }
}

