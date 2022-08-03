package com.example.miraclegame.domain.useCase

import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.repository.ScoreGetRepository

class GetScoreItemUseCase(private val scoreListRepository: ScoreGetRepository) {
    suspend fun getScoreItem(scoreItemId: Int): ScoreItem {
        return scoreListRepository.getScoreItem(scoreItemId)
    }
}