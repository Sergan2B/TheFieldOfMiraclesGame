package com.example.miraclegame.domain.useCase

import com.example.miraclegame.domain.model.ScoreItem
import com.example.miraclegame.domain.repository.ScoreEditRepository

class EditScoreItemUseCase(private val scoreListRepository: ScoreEditRepository) {
    suspend fun editScoreItem(scoreItem: ScoreItem) {
        scoreListRepository.editScoreItem(scoreItem)
    }
}