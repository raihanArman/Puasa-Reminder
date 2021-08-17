package com.example.puasareminder.repositories.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.puasareminder.repositories.local.dao.DatabaseDao
import com.example.puasareminder.repositories.local.entity.PuasaEntity
import com.example.puasareminder.util.DatabaseConverter

@Database(
    entities = [PuasaEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun databaseDao() : DatabaseDao
}