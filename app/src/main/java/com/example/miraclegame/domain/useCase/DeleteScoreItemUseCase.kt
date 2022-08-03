package com.example.miraclegame.domain.useCase

import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.repository.ScoreDeleteRepository

class DeleteScoreItemUseCase(private val scoreListRepository: ScoreDeleteRepository) {
    suspend fun deleteScoreItem(scoreItem: ScoreItem) {
        scoreListRepository.deleteScoreItem(scoreItem)
    }
}