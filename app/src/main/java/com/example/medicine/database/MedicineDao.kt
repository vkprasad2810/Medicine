package com.example.medicine.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MedicineDao {
    @Query("SELECT * FROM medicines")
    fun getAll(): List<Medicine>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( medicines: List<Medicine>)


}