package com.ecotracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ecotracker.data.models.EmissionRecord

@Database(
    entities = [EmissionRecord::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class EcoTrackerDatabase : RoomDatabase() {
    
    abstract fun emissionDao(): EmissionDao
    
    companion object {
        @Volatile
        private var INSTANCE: EcoTrackerDatabase? = null
        
        fun getDatabase(context: Context): EcoTrackerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EcoTrackerDatabase::class.java,
                    "ecotracker_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

