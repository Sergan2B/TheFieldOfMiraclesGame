package com.example.miraclegame.domain.repository

import com.example.miraclegame.domain.model.ScoreItem

interface ScoreGetRepository {
    suspend fun getScoreItem(scoreItemId: Int): ScoreItem
}