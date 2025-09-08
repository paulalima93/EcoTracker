package com.ecotracker.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ecotracker.data.models.EmissionRecord
import com.ecotracker.data.models.EmissionCategory
import java.util.Date

@Dao
interface EmissionDao {
    
    @Query("SELECT * FROM emission_records ORDER BY date DESC")
    fun getAllEmissions(): LiveData<List<EmissionRecord>>
    
    @Query("SELECT * FROM emission_records WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    fun getEmissionsByDateRange(startDate: Date, endDate: Date): LiveData<List<EmissionRecord>>
    
    @Query("SELECT * FROM emission_records WHERE category = :category ORDER BY date DESC")
    fun getEmissionsByCategory(category: EmissionCategory): LiveData<List<EmissionRecord>>
    
    @Query("SELECT SUM(carbonEmission) FROM emission_records WHERE date >= :startDate AND date <= :endDate")
    suspend fun getTotalEmissionsByDateRange(startDate: Date, endDate: Date): Double?
    
    @Query("SELECT SUM(carbonEmission) FROM emission_records WHERE category = :category AND date >= :startDate AND date <= :endDate")
    suspend fun getTotalEmissionsByCategoryAndDateRange(
        category: EmissionCategory,
        startDate: Date,
        endDate: Date
    ): Double?
    
    @Insert
    suspend fun insertEmission(emission: EmissionRecord): Long
    
    @Update
    suspend fun updateEmission(emission: EmissionRecord)
    
    @Delete
    suspend fun deleteEmission(emission: EmissionRecord)
    
    @Query("DELETE FROM emission_records")
    suspend fun deleteAllEmissions()
}

