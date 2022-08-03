package com.example.miraclegame.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miraclegame.data.model.ScoreDBModel

@Database(entities = [ScoreDBModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun scoreDao(): ScoreItemDao
}