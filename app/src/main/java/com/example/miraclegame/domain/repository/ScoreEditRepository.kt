package com.example.miraclegame.domain.repository

import com.example.miraclegame.domain.model.ScoreItem

interface ScoreEditRepository {
    suspend fun editScoreItem(scoreItem: ScoreItem)
}