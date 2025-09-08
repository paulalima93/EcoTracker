package com.ecotracker.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "emission_records")
data class EmissionRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val category: EmissionCategory,
    val activity: String,
    val value: Double,
    val unit: String,
    val carbonEmission: Double, // in kg CO2
    val date: Date,
    val description: String? = null
)

enum class EmissionCategory {
    TRANSPORT,
    ENERGY,
    FOOD,
    CONSUMPTION,
    OTHER
}

data class EmissionSummary(
    val category: EmissionCategory,
    val totalEmissions: Double,
    val percentage: Float
)

data class DailyEmission(
    val date: Date,
    val totalEmissions: Double
)

data class MonthlyEmission(
    val month: Int,
    val year: Int,
    val totalEmissions: Double
)

