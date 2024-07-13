package com.example.medicine.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Medicine::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}