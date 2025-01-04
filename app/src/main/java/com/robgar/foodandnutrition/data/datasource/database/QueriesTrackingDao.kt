package com.robgar.foodandnutrition.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robgar.foodandnutrition.data.QueryTracking
import kotlinx.coroutines.flow.Flow

@Dao
interface QueriesTrackingDao {

    @Query("SELECT * FROM QueryTracking WHERE prefix = :prefix")
    fun findQueryTrackingByPrefix(prefix: String): Flow<QueryTracking?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQueryTracking(queryTracking: QueryTracking)
}