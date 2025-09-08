package com.ecotracker.data.api

import com.ecotracker.data.models.CarbonEstimateResponse
import com.ecotracker.data.models.ElectricityEstimateRequest
import com.ecotracker.data.models.FlightEstimateRequest
import com.ecotracker.data.models.VehicleEstimateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CarbonInterfaceApi {
    
    @POST("estimates")
    suspend fun calculateElectricityEmissions(
        @Header("Authorization") authorization: String,
        @Body request: ElectricityEstimateRequest
    ): Response<CarbonEstimateResponse>
    
    @POST("estimates")
    suspend fun calculateVehicleEmissions(
        @Header("Authorization") authorization: String,
        @Body request: VehicleEstimateRequest
    ): Response<CarbonEstimateResponse>
    
    @POST("estimates")
    suspend fun calculateFlightEmissions(
        @Header("Authorization") authorization: String,
        @Body request: FlightEstimateRequest
    ): Response<CarbonEstimateResponse>
}

