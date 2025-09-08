package com.ecotracker.data.database

import androidx.room.TypeConverter
import com.ecotracker.data.models.EmissionCategory
import java.util.Date

class Converters {
    
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
    
    @TypeConverter
    fun fromEmissionCategory(category: EmissionCategory): String {
        return category.name
    }
    
    @TypeConverter
    fun toEmissionCategory(categoryString: String): EmissionCategory {
        return EmissionCategory.valueOf(categoryString)
    }
}

