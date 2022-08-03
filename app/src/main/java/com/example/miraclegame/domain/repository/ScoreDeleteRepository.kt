package com.example.miraclegame.domain.repository

import com.example.miraclegame.domain.model.ScoreItem

interface ScoreDeleteRepository {
    suspend fun deleteScoreItem(scoreItem: ScoreItem)
}