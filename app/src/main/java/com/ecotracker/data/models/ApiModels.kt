package com.ecotracker.data.models

import com.google.gson.annotations.SerializedName

// Carbon Interface API Models
data class CarbonEstimateResponse(
    val data: CarbonEstimateData
)

data class CarbonEstimateData(
    val id: String,
    val type: String,
    val attributes: CarbonEstimateAttributes
)

data class CarbonEstimateAttributes(
    @SerializedName("carbon_g")
    val carbonG: Double,
    @SerializedName("carbon_kg")
    val carbonKg: Double,
    @SerializedName("carbon_lb")
    val carbonLb: Double,
    @SerializedName("carbon_mt")
    val carbonMt: Double,
    @SerializedName("estimated_at")
    val estimatedAt: String
)

// Request models for different types of calculations
data class ElectricityEstimateRequest(
    val type: String = "electricity",
    @SerializedName("electricity_unit")
    val electricityUnit: String = "kwh",
    @SerializedName("electricity_value")
    val electricityValue: Double,
    val country: String = "br",
    val state: String? = null
)

data class VehicleEstimateRequest(
    val type: String = "vehicle",
    @SerializedName("distance_unit")
    val distanceUnit: String = "km",
    @SerializedName("distance_value")
    val distanceValue: Double,
    @SerializedName("vehicle_model_id")
    val vehicleModelId: String? = null
)

data class FlightEstimateRequest(
    val type: String = "flight",
    val passengers: Int,
    val legs: List<FlightLeg>,
    @SerializedName("distance_unit")
    val distanceUnit: String = "km"
)

data class FlightLeg(
    @SerializedName("departure_airport")
    val departureAirport: String,
    @SerializedName("destination_airport")
    val destinationAirport: String,
    @SerializedName("cabin_class")
    val cabinClass: String = "economy"
)

// Tip model
data class SustainabilityTip(
    val id: Int,
    val title: String,
    val description: String,
    val category: EmissionCategory,
    val impactLevel: ImpactLevel,
    val imageUrl: String? = null
)

enum class ImpactLevel {
    LOW, MEDIUM, HIGH
}

