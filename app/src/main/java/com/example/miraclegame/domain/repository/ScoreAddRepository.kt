package com.example.miraclegame.domain.repository

import com.example.miraclegame.domain.model.ScoreItem

interface ScoreAddRepository {
    suspend fun addScoreItem(scoreItem: ScoreItem)
}