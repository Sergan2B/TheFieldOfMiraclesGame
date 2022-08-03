package com.example.miraclegame.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score_item")
data class ScoreDBModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var score: Int,
)